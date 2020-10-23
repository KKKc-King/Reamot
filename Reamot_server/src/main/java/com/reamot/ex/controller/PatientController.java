package com.reamot.ex.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.reamot.ex.service.IPatientService;
import com.reamot.ex.entity.Patient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author maqh
 * @since 2020-10-18
 */
@Api(tags = {""})
@RestController
@RequestMapping("/patient")
public class PatientController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IPatientService patientService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Patient patient){
        return patientService.add(patient);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return patientService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Patient patient){
        return patientService.updateData(patient);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Patient> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return patientService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Patient findById(@PathVariable Long id){
        return patientService.findById(id);
    }

}
