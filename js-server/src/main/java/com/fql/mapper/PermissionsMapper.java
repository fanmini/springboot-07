package com.fql.mapper;

import com.fql.entity.MenuModel;
import com.fql.entity.RoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 樊乾浪
 * @date 2022/12/10 9:30
 * @company xxx
 */
@Mapper
public interface PermissionsMapper {
    /**
     * 查询所有角色
     */
    List<RoleModel> findAllRole();
    /**
     * 添加用户角色
     * @param uId : 用户id
     * @param  permissions： 角色id
     */
    int addRole(@Param("uId") int uId , @Param("permissions")Integer[] permissions);

    /**
     * 通过id查询用户的所有角色
     * @param userId
     */
    List<String> findByIdRoles(@Param("userId") Integer userId);
    /**
     * 通过id删除用户的所有角色
     */

    int delByIdRoles(@Param("uId") int uId);
    /**
     * 查询所有的权限
     */
    List<MenuModel> findAllPermission();
    /**
     *添加权限
     */
    int addPermission(@Param("uId")int uId, @Param("permissionId")Integer[] permissionId);
    /**
     * 通过id删除用户的所有权限
     */
    int delByIdPermissions(@Param("uId") int uId);

}
