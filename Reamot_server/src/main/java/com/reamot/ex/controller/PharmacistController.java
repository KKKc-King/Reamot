package com.reamot.ex.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.reamot.ex.service.IPharmacistService;
import com.reamot.ex.entity.Pharmacist;
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
@RequestMapping("/pharmacist")
public class PharmacistController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IPharmacistService pharmacistService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Pharmacist pharmacist){
        return pharmacistService.add(pharmacist);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return pharmacistService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Pharmacist pharmacist){
        return pharmacistService.updateData(pharmacist);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Pharmacist> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return pharmacistService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Pharmacist findById(@PathVariable Long id){
        return pharmacistService.findById(id);
    }

}
