package com.fql.service.imp;

import com.fql.common.RedisUtil;
import com.fql.entity.ResultModel;
import com.fql.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Qian
 */
public class BaseServiceImpl<T,ID,R extends JpaRepository<T,ID>> implements BaseService<T,ID> {
   /**
    * 表示操作当前类的持久层
    */
    protected final R repository;
    @Autowired
    protected RedisUtil redisUtil;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }
    /**
     *  实现了公共的添加方法
     */
    @Override
    public <S extends T> ResultModel save(S entity) {
        S data = repository.save(entity);
        ResultModel resultModel = ResultModel.getResultModel(data);
        return resultModel ;
    }

    /**
     * 实现了公共的删除方法
     * @param entity
     */
    @Override
    public ResultModel delete(T entity) {
        repository.delete(entity);
        return ResultModel.getResultModel("删除成功",null);
    }

    /**
     * 实现了公共的通过id删除数据方法
     * @param id
     * @return
     */
    @Override
    public ResultModel deleteById(ID id) {
        if(id != null){
            repository.deleteById(id);
            return ResultModel.getResultModel("删除成功",null);
        }
        return ResultModel.getResultModel("删除失败",null);
    }

    @Override
    public ResultModel findAll() {
        List<T> data = repository.findAll();
        return ResultModel.getResultModel("查询成功",data);
    }

    @Override
    public ResultModel findById(ID id) {
        Optional<T> data = repository.findById(id);
        return ResultModel.getResultModel("查询成功",data);
    }
}
