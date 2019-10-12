package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.services.IModelerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className ModelerController
 * @Author lulu
 * @Date 2019-09-06 22:30
 * activiti模型控制器
 **/
@Api("模型管理接口")
@RestController
@RequestMapping("api/models")
public class ModelerController {

    @Autowired
    private IModelerService iModelerService;

    @ApiOperation("新建一个空模型")
    @PostMapping("newModel")
    /**
     * @auther: lulu
     * @Description: 新建一个空model--增
     * @MethodName: newModel
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: []
     * @date: 2019-09-10 13:35
     **/
    public ServiceResponse newModel() {
        return iModelerService.newModel();
    }

    @ApiOperation("发布并部署模型")
    @PostMapping("{id}/deployment")
    /**
     * @auther: lulu
     * @Description: 发布模型为流程定义
     * @MethodName: deploy
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [id]
     * @date: 2019-09-10 13:00
     **/
    public ServiceResponse deploy(@ApiParam("模型Id") @PathVariable("id")String id) throws Exception {
        return iModelerService.deploy(id);
    }

    @ApiOperation("获取所有模型")
    @GetMapping
    /**
     * @auther: lulu
     * @Description: 获取所有模型--查
     * @MethodName: getAllModel
     * @return: java.lang.Object
     * @param: [rowSize, page]
     * @date: 2019-09-10 13:36
     **/
    public Object getAllModel(@ApiParam("每一页数据长度") @RequestParam(value = "rowSize", defaultValue = "1000", required = false) Integer rowSize, @ApiParam("将数据分成多少页") @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
        return iModelerService.getAllModel(rowSize, page);
    }


    @ApiOperation("根据ID得到单个模型")
    @GetMapping("/{id}")
    /**
     * @auther: lulu
     * @Description: 根据ID得到单个模型--查
     * @MethodName: getOne
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [id]
     * @date: 2019-09-10 13:35
     **/
    public ServiceResponse getOneModel(@ApiParam("模型Id") @PathVariable("id") String id) {
        return iModelerService.getOneModel(id);
    }


    @ApiOperation("根据ID删除模型")
    @DeleteMapping("/{id}")
    /**
     * @auther: lulu
     * @Description: 根据ID删除模型--删
     * @MethodName: deleteOne
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [id]
     * @date: 2019-09-10 13:36
     **/
    public ServiceResponse deleteOneModel(@ApiParam("模型Id") @PathVariable("id")String id){
        return iModelerService.deleteOneModel(id);
    }

    @ApiOperation("根据ID编辑模型")
    @PutMapping("{id}")
    /**
     * @auther: SNN
     * @Description: 对Id对应的model进行编辑--改
     * @MethodName: putOneModel
     * @return: com.snn.workflow.common.ServiceResponse
     * @param: [id]
     * @date: 2019/9/22 4:13
     **/
    public ServiceResponse putOneModel(@ApiParam("模型Id") @PathVariable("id")String id) {
        return iModelerService.putOneModel(id);
    }

}
