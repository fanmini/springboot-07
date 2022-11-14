package com.fql.controller;

import com.fql.entity.ImgModel;
import com.fql.entity.ResultModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/back/file")
public class Upload {
    @PostMapping("/upload")
    public ResultModel uploadFile(MultipartFile file) throws IOException {
        ImgModel imgModel = new ImgModel();
        if(file!= null){
            long l = System.currentTimeMillis();
            String originalFilename = l+"---"+file.getOriginalFilename();
                file.transferTo(new File("E:/code/WanXi/springboot-07/js-client/img/"+originalFilename));
                file.transferTo(new File("E:/code/WanXi/springboot-07/js-reception/img/"+originalFilename));
                imgModel.setSrc("/img/"+originalFilename);
        }
        return  ResultModel.getResultModel(1,imgModel);
    }
}
