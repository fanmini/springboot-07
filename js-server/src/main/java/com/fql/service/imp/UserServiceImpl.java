package com.fql.service.imp;

import com.fql.entity.*;
import com.fql.mapper.PermissionsMapper;
import com.fql.util.RedisKey;
import com.fql.mapper.UserMapper;
import com.fql.repository.jpa.UserRepository;
import com.fql.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qian
 * 用户业务逻辑的具体是实现
 * 通过基类的增删改查 以及用户的拓展业务
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel, Integer, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository, RedisKey.USER_KEY.getKey());
    }

    @Resource
    private UserMapper mapper;
    @Resource
    private PasswordEncoder pass;
    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private PermissionsMapper pMapper ;

    private final String permissionKey = "allPermission";

    /**
     * 模糊查询
     *
     * @param userModel
     * @return
     */
    @Override
    public ResultModel findAllByLike(UserModel userModel) {
        PageHelper.startPage(userModel.getPage(), userModel.getLimit());
        List<UserModel> all = mapper.findAll(userModel);
        PageInfo<UserModel> result = new PageInfo<>(all);
        Integer count = Math.toIntExact(result.getTotal());
        return ResultModel.getResultModel(count, all);
    }

    // 根据姓名查询用户是否存在
    @Override
    public UserModel findByName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public ResultModel setPwd(UserModel user) {
        if (user.getPassword() != null && user.getPassword() != "") {
            user.setPassword(pass.encode(user.getPassword()));
        }
        return super.save(user);
    }

    @Override
    public <S extends UserModel> ResultModel save(S entity) {
        // 密码不为空
        if (entity.getPassword() != null && entity.getPassword() != "") {
            entity.setPassword(pass.encode(entity.getPassword()));
        }
        return super.save(entity);
    }


    /**
     * 查询到所有的权限 并标注当前用户（userName）所拥有的权限通过setComponent("1")表示
     * @param userName
     * @return
     */
    public ResultModel findAllPermissionAndUserPermission(String userName ) {
        // 获取当前用户的权限
        UserDetailsEntity userDetails = (UserDetailsEntity) userDetailsService.loadUserByUsername(userName);
        List<String> permissions = userDetails.getPermissions();
        // 获取所有权限
        List<MenuModel> permission = redisUtil.getCacheObject(permissionKey);
        if (null == permission) {
            permission = pMapper.findAllPermission();
            // 加入缓存
            redisUtil.setCacheObject("permission", permission);
        }
        // 标记拥有权限
        for (String s : permissions) {
            for (int i = 0; i < permission.size(); i++) {
                String s1 = permission.get(i).getPerms();
                if(s1.equals(s)){
                    permission.get(i).setComponent("1");
                };
            }
        }
        return ResultModel.getResultModel(permission);
    }

    /**
     * 给当前用户设置权限
     * @param uId
     * @return
     */
    public ResultModel setPermission(int uId ,Integer[] permissionId ){
        // 添加用户权限     user====> permission
        // 1 删除清洗权限
        pMapper.delByIdPermissions(uId);
        // 2 添加权限到数据库
        int i = pMapper.addPermission(uId, permissionId);
        String mes = "特殊权限添加成功" ;
        if(i==0){
            mes="权限修改失败";
        }
        return ResultModel.getResultModel(mes,null);
    }


    /**
     * 0 代表拥有
     * @param userId
     * @return
     */
    public ResultModel findAllRolesByUserId(Integer userId){
        // 查询当前用户的角色
        List<String> byIdRoles = pMapper.findByIdRoles(userId);
        // 获取所有的角色
        List<RoleModel> allRole = pMapper.findAllRole();
        // 判断用户的角色拥有
        for (String s : byIdRoles) {
            for (RoleModel roleModel : allRole) {
                if (roleModel.getName().equals(s)) {
                    //拥有
                    roleModel.setStatus(0);
                }
            }
        }
        return  ResultModel.getResultModel(allRole);
    }

    /**
     * 设置用户角色
     * @param uId
     * @param permissions
     * @return
     */
    public ResultModel setRoles(int uId,Integer[] permissions){
        // 添加用户权限     user====> permission
        // 1 删除清洗权限
        int del = pMapper.delByIdRoles(uId);
        // 2 添加权限到数据库
        int i = pMapper.addRole(uId, permissions);
        String mes = "角色添加成功" ;
        if(i==0){
            mes="角色修改失败";
        }
        return ResultModel.getResultModel(mes,null);
    }


}
