package com.reamot.ex.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.reamot.ex.Response;
import com.reamot.ex.entity.Medicine;
import com.reamot.ex.service.IMedicineService;
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
@RequestMapping("/medicine")
public class MedicineController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IMedicineService medicineService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public Response add(@RequestBody Medicine medicine) {
        int add_num = medicineService.add(medicine);
        if (add_num == 1) {
            return Response.yes(1);
        }
        return Response.no();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public Response delete(@PathVariable("id") Long id) {
        int delete_num = medicineService.delete(id);
        if (delete_num != 0) {
            return Response.yes(1);
        }

        return Response.no();
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public Response update(@RequestBody Medicine medicine) {
        int update_num = medicineService.add(medicine);
        System.out.println(update_num);
        if (update_num == 1) {
            System.out.println("Update User success");
            return Response.yes();
        }
        System.out.println("Update User failed");
        return Response.no();
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public Response<IPage<Medicine>> findListByPage(@RequestParam Integer page,
                                                    @RequestParam Integer pageCount) {
        IPage<Medicine> listByPage = medicineService.findListByPage(page, pageCount);
        if (listByPage != null && listByPage.getSize() != 0) {
            return Response.yes(listByPage);
        }
        return Response.no();
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Response<Object> findById(@PathVariable Long id) {
        Medicine medicineById = medicineService.findById(id);
        if (medicineById != null) {
            return Response.yes(medicineById);
        }
        return Response.no();
    }

}
