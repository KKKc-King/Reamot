package com.reamot.ex.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.reamot.ex.Response;
import com.reamot.ex.entity.User;
import com.reamot.ex.service.IUserService;
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
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserService userService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public Response<Object> add(@RequestBody User user) {
        int add_num = userService.add(user);
        if (add_num == 1) {
            return Response.yes(1);
        }
        return Response.no();
    }


    @GetMapping("/login")
    public Response login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            QueryWrapper<User> user = new QueryWrapper<>();
            user.eq("username", username).eq("password", password);
            return Response.yes(userService.getOne(user));
        } catch (Exception e) {
            return Response.no();
        }
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public Response<Object> delete(@PathVariable("id") Long id) {
        int delete_num = userService.delete(id);
        if (delete_num != 0) {
            return Response.yes(1);
        }

        return Response.no();
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public Response<Object> update(@RequestBody User user) {
        System.out.println("Update User");
        int update_num = userService.updateData(user);
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
    public Response<IPage<User>> findListByPage(@RequestParam Integer page,
                                                @RequestParam Integer pageCount) {
        IPage<User> listByPage = userService.findListByPage(page, pageCount);
        if (listByPage != null && listByPage.getSize() != 0) {
            return Response.yes(listByPage);
        }
        return Response.no();
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Response<User> findById(@PathVariable Long id) {
        User userById = userService.findById(id);
        if (userById != null) {
            return Response.yes(userById);
        }
        return Response.no();
    }

    @ApiOperation(value = "id查询")
    @GetMapping("/findByusername")
    public Response<User> findByusername(@PathVariable String username) {
        try {
            QueryWrapper<User> user = new QueryWrapper<>();
            user.eq("username", username);
            return Response.yes(userService.getOne(user));
        } catch (Exception e) {
            return Response.no();
        }
    }

}
