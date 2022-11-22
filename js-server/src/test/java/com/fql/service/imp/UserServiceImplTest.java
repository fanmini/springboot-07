package com.fql.service.imp;

import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl service ;
    @Test
    void findAllByLike() {
        UserModel model = new UserModel();
        model.setPage(1);
        model.setLimit(2);
        ResultModel allByLike = service.findAllByLike(model);
        System.out.println(allByLike);
    }
}