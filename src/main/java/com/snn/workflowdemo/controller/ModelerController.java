package com.snn.workflowdemo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.snn.workflowdemo.common.ServiceResponse;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className ModelerController
 * @Author lulu
 * @Date 2019-09-06 22:30
 * activiti模型控制器
 **/
@RestController
@RequestMapping("models")
public class ModelerController {

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("newModel")
    /**
     * @auther: lulu
     * @Description: 新建一个空模型
     * @MethodName: newModel
     * @return: com.snn.workflowdemo.common.ServiceResponse
     * @param: []
     * @date: 2019-09-10 13:35
     **/
    public ServiceResponse newModel() throws UnsupportedEncodingException {
        //初始化一个空模型
        Model model = repositoryService.newModel();

        //设置一些默认信息
        String name = "new-process";
        String description = "";
        int revision = 1;
        String key = "process";

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("TargetURL", "/activiti/modeler.html?modelId="+id);
        return ServiceResponse.createBySuccess("新建模型成功，将TargetURL与BaseURL拼接进入流程可视化界面！", data);
    }

    @PostMapping("{id}/deployment")
    /**
     * @auther: lulu
     * @Description: 发布模型为流程定义
     * @MethodName: deploy
     * @return: com.snn.workflowdemo.common.ServiceResponse
     * @param: [id]
     * @date: 2019-09-10 13:00
     **/
    public ServiceResponse deploy(@PathVariable("id")String id) throws Exception {

        //获取模型
        Model modelData = repositoryService.getModel(id);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return ServiceResponse.createByErrorMessage("模型数据为空，请先设计流程并成功保存，再进行发布。");
        }

        JsonNode modelNode = new ObjectMapper().readTree(bytes);

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if(model.getProcesses().size() == 0){
            return ServiceResponse.createByErrorMessage("数据模型不符要求，请至少设计一条主线流程。");
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        // 发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8"))
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);

        return ServiceResponse.createBySuccessMessage("部署成功！");
    }

    @GetMapping("/{id}")
    /**
     * @auther: lulu
     * @Description: 根据ID得到单个模型
     * @MethodName: getOne
     * @return: com.snn.workflowdemo.common.ServiceResponse
     * @param: [id]
     * @date: 2019-09-10 13:35
     **/
    public ServiceResponse getOne(@PathVariable("id") String id) {
        Model model = repositoryService.createModelQuery().modelId(id).singleResult();
        if (model == null) {
            return ServiceResponse.createByErrorMessage("查询失败！");
        }
        return ServiceResponse.createBySuccess("查询成功", model);
    }

    @GetMapping
    /**
     * @auther: lulu
     * @Description: 获取所有模型
     * @MethodName: getList
     * @return: java.lang.Object
     * @param: [rowSize, page]
     * @date: 2019-09-10 13:36
     **/
    public Object getList(@RequestParam(value = "rowSize", defaultValue = "1000", required = false) Integer rowSize, @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
        List<Model> list = repositoryService.createModelQuery().listPage(rowSize * (page - 1), rowSize);
        long count = repositoryService.createModelQuery().count();

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("TotalRows", count);
        data.put("TotalPages", (int) (count/rowSize+1));
        data.put("RowSize", rowSize);
        data.put("CurrentPage", page);
        data.put("list", list);

        return ServiceResponse.createBySuccess("查询成功", data);
    }

    @DeleteMapping("/{id}")
    /**
     * @auther: lulu
     * @Description: 根据ID删除模型
     * @MethodName: deleteOne
     * @return: com.snn.workflowdemo.common.ServiceResponse
     * @param: [id]
     * @date: 2019-09-10 13:36
     **/
    public ServiceResponse deleteOne(@PathVariable("id")String id){
        repositoryService.deleteModel(id);
        return ServiceResponse.createBySuccessMessage("删除成功！");
    }

}
