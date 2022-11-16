package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.ResultModel;
import com.fql.entity.StudentModel;
import com.fql.mapper.StudentMapper;
import com.fql.mapper.UserMapper;
import com.fql.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentModel,Integer, StudentRepository>  {
    public StudentServiceImpl(StudentRepository studentRepository) {
        super(studentRepository,RedisKeyPrefixEnum.STUDENT_KEY.getKey());
    }

    @Resource
    private StudentMapper mapper ;


    @Override
    public ResultModel findAllByLike(StudentModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }
}
