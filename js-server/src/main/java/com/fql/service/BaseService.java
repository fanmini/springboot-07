package com.fql.service;

import com.fql.entity.Mail;
import com.fql.entity.ResultModel;

/**
 * 通过基类提供常用的基本方法并让子类继承以后提业务逻辑
 * @author Qian
 */
public interface BaseService<T, ID> {
    /**
     * save all
     * 添加方法
     */
    <S extends T> ResultModel save(S entity) ;

    /**
     * 通过id删除
     *
     * @param id
     */
    ResultModel deleteById(ID id);

    /**
     * 查询全部
     */
    ResultModel findAll();

    /**
     * 通过id查询
     * @param id
     * @return
     */
    ResultModel findById(ID id);

    ResultModel findAllByLike(T entity);


}
