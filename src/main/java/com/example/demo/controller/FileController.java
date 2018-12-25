package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController {

    @GetMapping(value = "/file")
    public String file() {
        return "file";
    }

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request)
    throws Exception{

        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        System.out.println("11");
        String filePath = "";
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        try{
            filePath = ResourceUtils.getURL("src/main/resources/static/images").getPath();
        }catch (Exception e){

        }

        fileName = UUID.randomUUID() + suffixName; // 新文件名
        request.setAttribute("imgname",fileName);
        String filename = filePath + "\\"+fileName;
        File dest = new File(filename);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }
}