package com.fql.controller;

import com.fql.entity.BankEsEntity;
import com.fql.service.imp.BankEsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qian
 */
@RestController
@RequestMapping("/bank")
public class BankEsController {
    private BankEsService bes ;
    BankEsController(BankEsService bes){
        this.bes=bes ;
    }

    @GetMapping("/banks")
    public List<BankEsEntity> findAll(){
        List<BankEsEntity> all = bes.findAll();
        return all ;
    }

    @PostMapping("/bank")
    public BankEsEntity save(@RequestBody BankEsEntity bee){
       return  bes.save(bee);
    }

    // 删除
    @PostMapping("/delete")
    public boolean delete(@RequestBody BankEsEntity bee){
        return bes.delete(bee);
    }

    // 邮箱搜索
    @GetMapping("/email/{email}")
    public List<BankEsEntity> findByEmail(@PathVariable String email){
        return bes.searchByEmail(email);
    }

}
