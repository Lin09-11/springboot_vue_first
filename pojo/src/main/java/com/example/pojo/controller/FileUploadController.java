package com.example.pojo.controller;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    /**
     * 当表单的enctype="multipart/form-data"时,可以使用MultipartFile 获取上传
     * 的文件数据，再通过transferTo方法将其写入到磁盘中
     * @param nickname
     * @param photo
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String up(String nickname, MultipartFile photo , HttpServletRequest request) throws IOException {
        System.out.println(nickname);
        //获取图片的原始名称
        //2685f3421220bd0b8c37d1fab92f813.jpg
        System.out.println(photo.getOriginalFilename());
        //获取文件类型
        //image/jpeg
        System.out.println(photo.getContentType());

        String path=request.getServletContext().getRealPath("/upload/");
        System.out.println(path);//idea动态分配
        saveFile(photo,path);
        return "上传成功";
    }

    /**
     * 文件存储
     * @param photo
     * @param path
     * @throws IOException
     */
    public void saveFile(MultipartFile photo,String path) throws IOException {
        //判断存储的目录是否存在，如果不存在则创建
        File dir = new File(path);
        if(!dir.exists()){//判断路径是否存在
            //创建目录
            dir.mkdir();
        }
        //最终存储文件的地址
        File file=new File(path+photo.getOriginalFilename());
        photo.transferTo(file);
    }
}
