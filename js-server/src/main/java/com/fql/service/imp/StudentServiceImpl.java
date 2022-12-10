package com.fql.service.imp;

import com.fql.util.RedisKey;
import com.fql.entity.ResultModel;
import com.fql.entity.StudentModel;
import com.fql.mapper.StudentMapper;
import com.fql.repository.jpa.StudentRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentModel,Integer, StudentRepository>  {
    public StudentServiceImpl(StudentRepository studentRepository) {
        super(studentRepository, RedisKey.STUDENT_KEY.getKey());
    }

    @Resource
    private StudentMapper mapper ;


    @Override
    public ResultModel findAllByLike(StudentModel entity) {
        PageHelper.startPage(entity.getPage(),entity.getLimit());
        List<StudentModel> all = mapper.findAll(entity);
        PageInfo<StudentModel> result = new PageInfo<>(all);
        Integer count = Math.toIntExact(result.getTotal());
        return ResultModel.getResultModel(count,all);



    }
}
