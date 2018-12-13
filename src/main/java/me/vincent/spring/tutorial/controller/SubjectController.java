package me.vincent.spring.tutorial.controller;

import me.vincent.spring.tutorial.viewmodel.ResultBean;
import me.vincent.spring.tutorial.domain.Subject;
import me.vincent.spring.tutorial.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 加注释
 *
 * @description: 科目RESTAPI接口
 * @author: Vincent Huang
 * @version:
 */

@Api(description = "科目的增删改查操作", tags = {"科目"})
@RestController
@RequestMapping("subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value="科目列表", notes = "获取所有科目内容")
    @GetMapping
    public ResultBean<List<Subject>> findAllSubjects(){
        List<Subject> results = subjectService.findAllSubjects();
        return ResultBean.<List<Subject>>OK(results, "Succeed in finding all subjects.");
    }

    @ApiOperation(value = "新增的科目", notes = "根据上传的信息创建科目")
    @PostMapping
    public ResultBean<Subject> addSubject(
            @RequestBody @Validated @ApiParam(value = "新增的科目对象", required = true) Subject subject){
        Subject result = subjectService.addSubject(subject);
        return ResultBean.<Subject>OK(result, "Succeed in adding subject");
    }

    @DeleteMapping("/{name}")
    public ResultBean deleteSubject(
            @PathVariable @ApiParam(value = "需要删除的科目名称", required = true) String name){
        subjectService.deleteSubjectByName(name);
        return ResultBean.OK();
    }
}
