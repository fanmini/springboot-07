package com.fql.service.imp;

import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.mapper.PermissionsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl service ;
    @Resource
    private PermissionsMapper mapper ;
    @Test
    void findAllByLike() {
        UserModel model = new UserModel();
        model.setPage(1);
        model.setLimit(2);
        ResultModel allByLike = service.findAllByLike(model);
        System.out.println(allByLike);
    }
    @Test
    void findAllRoles(){
        List<String> byIdRoles = mapper.findByIdRoles(2);
        ResultModel allRolesByUserId = service.findAllRolesByUserId(2);
    }

}