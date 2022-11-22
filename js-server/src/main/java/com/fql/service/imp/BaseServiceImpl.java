package com.fql.service.imp;

import com.fql.common.ErrorMsgCodeEnum;
import com.fql.util.RedisUtil;
import com.fql.entity.ResultModel;
import com.fql.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Qian
 * 所有实体对象的基本增删改查操作，并实现了redis 缓存， 双写一致性（并不是百分百的一致性）
 * 通过spring的@transactional开启事务 保持数据的ACID
 *  双写一致实现:
 *  read : 先去redis查找对应的数据是否存在如果命中返回 未命中 在去数据库查找对应数据
 *          查找成功以后，在更新缓存
 *
 *  write ：先对数据库进行增删改操作，在删除redis缓存（直接删除缓存要快的多）
 *          存入缓存设置过期时间用于兜底数据的一致性无法保证通过key的过期时间来进行数据的更新
 *          保证数据的一致性
 *
 *
 */
@Slf4j
public abstract class  BaseServiceImpl<T,ID,R extends JpaRepository<T,ID>> implements BaseService<T,ID> {
   /**
    * 表示操作当前类的持久层
    * @repository: 当前的操作数据库对象
    * @redisUtil :操作redis的工具类
    */
    protected final R repository;

    @Autowired
    protected RedisUtil redisUtil;


    /**
     * 对应的产品类型存储缓存的redis前缀
     * @param repository
     *
     * 通过子类赋值redis的key方式实现了公共的通过redis+mysql实现增删改查
     *
     */
    protected String prefixKey ;


    /**
     * 通过构造器注入当前操作数据库的的对象类
     * @param repository
     */
    public BaseServiceImpl(R repository,String prefixKey) {
        this.repository = repository;
        this.prefixKey = prefixKey ;
    }



    @Override
    @Transactional
    public <S extends T> ResultModel save(S entity){
        if(Objects.isNull(entity)){
            return ResultModel.getResultModel(ErrorMsgCodeEnum.ERROR_ADD);
        }
        // 更新数据库
        S data = repository.save(entity);
        // 删除缓存
        try {
            Field id = null;
            id = entity.getClass().getSuperclass().getDeclaredField("id");
            id.setAccessible(true);
            redisUtil.deleteObject(prefixKey+":"+id.get(entity));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        redisUtil.deleteObject(prefixKey+":all");
        return ResultModel.getResultModel(1,data);
    }


    /**
     * 实现了公共的通过id删除数据方法
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ResultModel deleteById(ID id) {
        if(id == null){
            return ResultModel.getResultModel(ErrorMsgCodeEnum.ERROR_DEL.toString(),null);
        }
        // 删除数据库
        repository.deleteById(id);
        // 删除缓存
        redisUtil.deleteObject(prefixKey+":"+id);
        redisUtil.deleteObject(prefixKey+":all");
        return ResultModel.getResultModel("删除成功",null);
    }

    @Override
    public ResultModel findAll() {
        String key = prefixKey+":all";
        // 缓存查询
        List<T> data = redisUtil.getCacheList(key);
        ResultModel result = ResultModel.getResultModel("查询成功", data);
        if(data.size()<1){
            // 数据库查询
            data = repository.findAll();
            if(data.size()<1){
                return result;
            }
            redisUtil.setCacheList(key,data);
            redisUtil.expire(key,1L,TimeUnit.HOURS);
        }
        // 返回数据
        result.setData(data);
        return result;
    }

    @Override
    public ResultModel findById(ID id) {
        if(Objects.isNull(id)){
            log.warn("user id is error");
            return ResultModel.getResultModel("没有找到对应用户",null);
        }
        String key = prefixKey+":"+id;
        // 缓存查询用户信息
        T data = redisUtil.getCacheObject(key);
        if(Objects.isNull(data)){
            // 数据库查询
            data = (T) repository.findById(id).orElse(null);
        }
        if(Objects.isNull(data)){
            return ResultModel.getResultModel("未找到该信息",data);
        }
        // 存入redis
        redisUtil.setCacheObject(key,data,1,TimeUnit.HOURS);
        // 返回结果
        return ResultModel.getResultModel("查询成功",data);
    }

    @Override
    public  ResultModel findAllByLike(T entity){
        return null ;
    }
}
