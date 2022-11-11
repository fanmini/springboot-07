package com.fql.controller;

import com.fql.entity.StudentModel;
import com.fql.service.imp.StudentServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/back/student")
@Api(value = "api接口",tags = {"学员管理"})
public class StudentController extends BaseController<StudentModel,Integer> {
    public StudentController(StudentServiceImpl service) {
        super(service);
    }
}
