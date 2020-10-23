package com.reamot.ex.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.reamot.ex.Response;
import com.reamot.ex.entity.Doctor;
import com.reamot.ex.service.IDoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author maqh
 * @since 2020-10-18
 */
@Api(tags = {""})
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IDoctorService doctorService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public Response add(@RequestBody Doctor doctor) {
        int add_num = doctorService.add(doctor);
        if (add_num == 1) {
            return Response.yes(1);
        }
        return Response.no();
    }


    @GetMapping("/login")
    public Response login(@RequestParam("username") String name, @RequestParam("password") String password) {
        QueryWrapper<Doctor> doctorQueryWrapper = new QueryWrapper<>();
        if (name != null || password != null) {
            doctorQueryWrapper.eq("name", name).eq("password", password);
            Doctor one = doctorService.getOne(doctorQueryWrapper);
            if (one != null) {
                return Response.yes(one);
            } else {
                return Response.no();
            }
        } else {
            return Response.no();
        }
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id) {
        return doctorService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Doctor doctor) {
        return doctorService.updateData(doctor);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Doctor> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount) {
        return doctorService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Doctor findById(@PathVariable Long id) {
        return doctorService.findById(id);
    }

}
