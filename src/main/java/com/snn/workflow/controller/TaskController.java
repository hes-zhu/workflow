package com.snn.workflow.controller;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.snn.workflow.common.Const;
import com.snn.workflow.common.ResponseCode;
import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.User;
import com.snn.workflow.services.IFileService;
import com.snn.workflow.services.ITaskService;
import com.snn.workflow.util.PropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className TaskController
 * @Author SNN
 * @Date 2019/9/22 4:37
 **/
@Api("任务管理接口")
@RestController
@RequestMapping("api/task")
public class TaskController {


    @Autowired
    private ITaskService iTaskService;
    @Autowired
    private IFileService iFileService;

    @ApiOperation("获取所有任务")
    @GetMapping
    /**
     * @auther: SNN
     * @Description: 获取所有任务
     * @MethodName: getAllTask
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [rowSize, page]
     * @date: 2019/9/23 20:59
     **/
    public ServiceResponse getAllTask(@ApiParam("每一页数据长度") @RequestParam(value = "rowSize", defaultValue = "1000", required = false) Integer rowSize, @ApiParam("将数据分成多少页") @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
        return iTaskService.getAllTask(rowSize, page);
    }

    @ApiOperation("根据key启动流程")
    @PostMapping("boot")
    /**
     * @auther: SNN
     * @Description: 根据key启动流程
     * @MethodName: startTask
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [key]
     * @date: 2019/9/23 20:59
     **/
    public ServiceResponse startTask(@ApiParam("流程定义key") @RequestParam("key")String key) {
        return iTaskService.startTask(key);
    }

    @ApiOperation("根据用户名查询任务")
    @GetMapping("findTask")
    /**
     * @auther: SNN
     * @Description: 根据用户名查询任务
     * @MethodName: findTask
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [username]
     * @date: 2019/9/23 20:59
     **/
    public ServiceResponse findTask(@ApiParam("当前登录系统的用户名") @RequestParam("username")String username) {
        return iTaskService.checkTask(username);
    }

    @ApiOperation("根据任务Id完成任务")
    @PostMapping("completeTask")
    /**
     * @auther: SNN
     * @Description: 根据任务Id完成任务
     * @MethodName: CompleteTask
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [taskId]
     * @date: 2019/9/23 21:00
     **/
    public ServiceResponse CompleteTask(@ApiParam("任务Id") @RequestParam("taskId") String taskId, @ApiParam("任务数据对象") @RequestParam(value = "dataMap", required=false, defaultValue = "{}") String data) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(data, map.getClass());
        return iTaskService.completeTask(taskId, map);
    }

    @ApiOperation("获取流程变量")
    @GetMapping("getVaribles")
    /**
     * @auther: SNN
     * @Description: 获取流程变量
     * @MethodName: getVaribles
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [executionId, variable]
     * @date: 2019/9/30 19:33
     **/
    public ServiceResponse getVaribles(@ApiParam("执行Id") @RequestParam("executionId") String executionId, @ApiParam("流程变量") @RequestParam("variable")String variable) {
        return iTaskService.getVarables(executionId, variable);
    }

    @ApiOperation("查询已完成任务名称")
    @GetMapping("getTaskName")
    /**
     * @auther: SNN
     * @Description: 查询已完成任务名称
     * @MethodName: queryHistoryTask
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [executionId]
     * @date: 2019/9/30 20:01
     **/
    public ServiceResponse queryHistoryTask(@ApiParam("执行Id") @RequestParam("executionId") String executionId) {
        return iTaskService.getTaskName(executionId);
    }

    @ApiOperation("上传任务过程中需要提交的附件")
    @PostMapping("upload")
    /**
     * @auther: SNN
     * @Description: 上传任务过程中需要提交的附件
     * @MethodName: upload
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [session, file, request]
     * @date: 2019/9/25 20:46
     **/
    public ServiceResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request) {
//        User user = (User)session.getAttribute(Const.CURRENT_USER);
//        if(user == null) {
//            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员！");
//        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        if (targetFileName.equals(null)) {
            return ServiceResponse.createByErrorMessage("上传失败");
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServiceResponse.createBySuccess(fileMap);
    }

    @ApiOperation("任务执行中需要下载的文件")
    @GetMapping("download")
    /**
     * @auther: SNN
     * @Description: 任务执行中需要下载的文件
     * @MethodName: download
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [filePath, response]
     * @date: 2019/9/25 20:47
     **/
    public ServiceResponse download(@RequestParam("filePath")@ApiParam("文件路径") String filePath, @RequestParam("fileName")@ApiParam("文件名-包括后缀") String fileName, HttpServletResponse response) throws IOException {
        // 文件名
        System.out.println("入口："+fileName);
        // 文件后缀名
        String fileSuffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println("后缀"+fileSuffixName);
        response.reset();
        response.setContentType("application/" + fileSuffixName + ";" + "charset = UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        byte[] outPutStream = iFileService.downloadFile(filePath, fileName);
        if (outPutStream == null) {
            return ServiceResponse.createByErrorMessage("获取下载文件失败！");
        } else {
            response.getOutputStream().write(outPutStream);
        }
        return ServiceResponse.createBySuccess();
    }
}
