package com.fql.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class CopyImg {
     public void copy(String filePath,String newFile){
         BufferedInputStream bis = null;
         BufferedOutputStream oos = null;
         try {
             File file = new File(filePath);
             FileInputStream fis = new FileInputStream(file);
             bis = new BufferedInputStream(fis);
             long length = file.length();
             byte[] bytes = new byte[(int) length];
             bis.read(bytes);
             oos = new BufferedOutputStream(new FileOutputStream(newFile));
             oos.write(bytes);
             oos.flush();
         } catch (IOException e) {
             e.printStackTrace();
         }

         try {
             // 关闭流
             if (oos!=null){
                 oos.close();
             }
             if (bis!=null){
                 bis.close();
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         log.debug("图片文件copy");
    }
}
