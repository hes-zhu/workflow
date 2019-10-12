package com.snn.workflow.services.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.services.IModelerService;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.snn.workflow.common.Const.getPageData;

/**
 * @className ModelerServiceImpl
 * @Author SNN
 * @Date 2019/9/23 20:27
 **/
@Service("iModelerService")
public class ModelerServiceImpl implements IModelerService {

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public ServiceResponse newModel() {
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
        try {
            repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("TargetURL", "activiti/modeler.html?modelId="+id);
        return ServiceResponse.createBySuccess("新建模型成功，将TargetURL与BaseURL拼接进入流程可视化界面！", data);
    }

    @Override
    public ServiceResponse deploy(String id) throws Exception {
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

        Map<String, Object> deploymentMap = new HashMap<>();
        deploymentMap.put("id", deployment.getId());
        deploymentMap.put("name", deployment.getName());
        deploymentMap.put("deploymentTime", deployment.getDeploymentTime());

        return ServiceResponse.createBySuccess("部署成功！", deploymentMap);
    }

    @Override
    public Object getAllModel(Integer rowSize, Integer page) {
        List<Model> models = repositoryService.createModelQuery().listPage(rowSize * (page - 1), rowSize);
        long count = repositoryService.createModelQuery().count();

        List<Object> modelList = new ArrayList<>();

        for (Model model : models) {
            modelList.add(getModelInfo(model));
        }

        Map<String, Object> data = new HashMap<String, Object>();
        getPageData(rowSize, page, count, data);
        data.put("modelList", modelList);

        return ServiceResponse.createBySuccess("查询成功", data);
    }

    @Override
    public ServiceResponse getOneModel(String id) {
        Model model = repositoryService.createModelQuery().modelId(id).singleResult();
        if (model == null) {
            return ServiceResponse.createByErrorMessage("查询失败！");
        }

        List<Object> list = new ArrayList<>();
        list.add(getModelInfo(model));

        return ServiceResponse.createBySuccess("查询成功", list);
    }


    @Override
    public ServiceResponse deleteOneModel(String id){
        repositoryService.deleteModel(id);
        return ServiceResponse.createBySuccessMessage("删除成功！");
    }

    @Override
    public ServiceResponse putOneModel(String id) {
        if (StringUtils.isNotBlank(id)) {
            Map<String, String> data = new HashMap<>();
            data.put("TargetURL", "/activiti/modeler.html?modelId=" + id);
            return ServiceResponse.createBySuccess("将TargetURL与BaseURL拼接进入流程可视化界面！", data);
        }
        return ServiceResponse.createByErrorMessage("参数错误");
    }


    private Map<String, Object> getModelInfo(Model model) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("id", model.getId());
        modelMap.put("version", model.getVersion());
        modelMap.put("name", model.getName());
        modelMap.put("key", model.getKey());
        modelMap.put("createTime", model.getCreateTime());
        modelMap.put("lastUpdateTime", model.getLastUpdateTime());
        modelMap.put("deploymentId", model.getDeploymentId());
        return modelMap;
    }

}
