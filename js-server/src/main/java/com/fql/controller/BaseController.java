package com.fql.controller;


import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.service.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qian
 * Controller 基类 提供了基本的增删改查
 */
public class BaseController<Entity, ID> {
    protected final BaseService<Entity, ID> service;

    public BaseController(BaseService service) {
        this.service = service;
    }

    @ApiOperation(value = "传入一个当前模型的对象，添加一个当前对象", httpMethod = "POST")
    @PostMapping("/add")
    public ResultModel add(@RequestBody Entity entity) {
        return service.save(entity);
    }


    @ApiOperation(value = "通过传入一个当前对象的id，根据传入的id进行参数匹配删除", httpMethod = "DELETE")
    @DeleteMapping("/del/{id}")
    public ResultModel delById(@PathVariable("id") ID id) {
        return service.deleteById(id);
    }

    @ApiOperation(value = "传入当前模型对象，根据传入的id进行修改对象的其他值", httpMethod = "PUT")
    @PutMapping("/set")
    public ResultModel set(@RequestBody Entity entity) {
        return service.save(entity);
    }


    @ApiOperation(value = "直接查询全部", httpMethod = "GET")
    @GetMapping("/query")
    public ResultModel findAll() {
        return service.findAll();
    }


    @ApiOperation(value = "通过传入一个当前对象的id，根据传入的id进行查询", httpMethod = "GET")
    @GetMapping("/query/{id}")
    public ResultModel findById(@PathVariable("id") ID id) {
        return service.findById(id);
    }

    @ApiOperation(value = "传入当前模型对象，根据传入属性值模糊查询对应的数据", httpMethod = "GET")
    @GetMapping("/findAllByLike")
    public ResultModel findAllByLike(Entity entity) {
        return service.findAllByLike(entity);
    }


}
