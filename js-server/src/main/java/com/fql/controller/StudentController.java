package com.fql.controller;

import com.fql.entity.ResultModel;
import com.fql.entity.StudentModel;
import com.fql.service.imp.StudentServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/back/student")
@Api(value = "api接口", tags = {"学员管理"})
public class StudentController extends BaseController<StudentModel, Integer> {
    public StudentController(StudentServiceImpl service) {
        super(service);
    }

    @PreAuthorize("hasAnyAuthority('system:back:student','system:admin:all')")
    @Override
    public ResultModel add(StudentModel studentModel) {
        return super.add(studentModel);
    }

    @PreAuthorize("hasAnyAuthority('system:back:student','system:admin:all')")
    @Override
    public ResultModel delById(Integer integer) {
        return super.delById(integer);
    }

    @PreAuthorize("hasAnyAuthority('system:back:student','system:admin:all')")
    @Override
    public ResultModel set(StudentModel studentModel) {
        return super.set(studentModel);
    }
}
