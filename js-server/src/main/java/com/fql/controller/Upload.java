package com.fql.controller;

import com.fql.entity.ImgModel;
import com.fql.entity.ResultModel;
import com.fql.util.CopyImg;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/back/file")
public class Upload {
    @Resource
    private CopyImg c ;

    @PostMapping("/upload")
    public ResultModel uploadFile(MultipartFile file) throws IOException {
        ImgModel imgModel = new ImgModel();
        String originalFilename;
        String path;
        String newPath ="E:/code/WanXi/springboot-07/js-reception/img/";
        if (file != null) {
            long l = System.currentTimeMillis();
            originalFilename = l + "---" + file.getOriginalFilename();
            path = "E:/code/WanXi/springboot-07/js-client/img/" + originalFilename;
            file.transferTo(new File(path));
            imgModel.setSrc("/img/" + originalFilename);
            c.copy(path,newPath);
        }

        return ResultModel.getResultModel(1, imgModel);
    }
}
