package com.fql.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.StudentModel;
import com.fql.entity.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface StudentMapper extends BaseMapper<StudentModel> {
    List<StudentModel> findAll(StudentModel ssm);

}
