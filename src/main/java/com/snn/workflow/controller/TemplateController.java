package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.Filetemplate;
import com.snn.workflow.services.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className TemplateController
 * @Author lulu
 * @Date 2019/12/3 下午3:50
 **/
@RestController
@RequestMapping("api/template")
public class TemplateController {

    @Autowired
    private ITemplateService templateService;

    @GetMapping
    public ServiceResponse selectAll() {
        return templateService.selectAll();
    }

    @PostMapping
    public ServiceResponse addOrder(Filetemplate filetemplate) {
        return templateService.addOrder(filetemplate);
    }

    @DeleteMapping("{id}")
    public ServiceResponse deleteOrder(@PathVariable("id") String id) {
        return templateService.deleteOrder(Integer.parseInt(id));
    }

    @PutMapping
    public ServiceResponse updateOrder(Filetemplate filetemplate) {
        return templateService.updateOrder(filetemplate);
    }

}
