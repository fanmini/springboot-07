package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.StudentModel;
import com.fql.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentModel,Integer, StudentRepository>  {
    public StudentServiceImpl(StudentRepository studentRepository) {
        super(studentRepository,RedisKeyPrefixEnum.STUDENT_KEY.getKey());
    }


}
