package com.fql.controller;

import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.service.imp.UserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qian
 */
@RestController()
@RequestMapping("/back/user")
@Api(value = "api接口", tags = {"用户管理"})

public class UserController extends BaseController<UserModel, Integer> {
    @Autowired
    UserServiceImpl userService;

    public UserController(UserServiceImpl service) {
        super(service);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all')")
    @PutMapping("/pwd")
    public ResultModel setPwd(@RequestBody UserModel user) {
        return userService.setPwd(user);
    }


    @PreAuthorize("hasAnyAuthority('system:admin:all')")
    @Override
    public ResultModel add(UserModel userModel) {
        return super.add(userModel);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all')")
    @Override
    public ResultModel delById(Integer integer) {
        return super.delById(integer);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all')")
    @Override
    public ResultModel set(UserModel userModel) {
        return super.set(userModel);
    }



    @PreAuthorize("hasAnyAuthority('system:admin:all')")
    @GetMapping("/permissions/{userName}")
    public ResultModel findAllPermissionAndUserPermission(@PathVariable("userName")String userName) {
      return   userService.findAllPermissionAndUserPermission(userName);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all')")
    @PostMapping("/permissions/{userId}")
    public ResultModel setUserPermission(@PathVariable("userId")int uId,@RequestBody Integer[] permissions) {
        return  userService.setPermission(uId ,permissions);
    }

    /**
     * 查询到所有的角色 并标注当前用户（userName）所拥有的角色
     * @param userId
     * @return
     */
    @PreAuthorize("hasAnyAuthority('system:admin:all')")
    @GetMapping("/role/{userId}")
    public ResultModel findAllRoleAndUserRole(@PathVariable("userId")Integer userId) {
        return  userService.findAllRolesByUserId(userId);
    }

    /**
     * 给当前用户设置角色
     * @param uId
     * @return
     */
    @PreAuthorize("hasAnyAuthority('system:admin:all')")
    @PostMapping("/role/{userId}")
    public ResultModel setUserRoles(@PathVariable("userId")int uId,@RequestBody Integer[] permissions) {
        return  userService.setRoles(uId ,permissions);
    }





}
