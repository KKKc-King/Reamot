package com.reamot.ex.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.reamot.ex.service.IPrescriptionService;
import com.reamot.ex.entity.Prescription;
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
@RequestMapping("/prescription")
public class PrescriptionController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IPrescriptionService prescriptionService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Prescription prescription){
        return prescriptionService.add(prescription);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return prescriptionService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Prescription prescription){
        return prescriptionService.updateData(prescription);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Prescription> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return prescriptionService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Prescription findById(@PathVariable Long id){
        return prescriptionService.findById(id);
    }

}
