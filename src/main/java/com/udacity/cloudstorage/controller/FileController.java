package com.udacity.cloudstorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Shubham Sharma
 * @date 6/4/20
 */
@Controller
public class FileController {

    @Autowired
    private FilesService filesService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/files")
    public String saveFile(Authentication authentication, MultipartFile fileUpload, Model model) throws IOException {
        User user = (User) authentication.getPrincipal();
        if (fileUpload.isEmpty()) {
            model.addAttribute("error",true);
            return "result.html";
        }
        if(filesService.isFileAdded(fileUpload.getOriginalFilename())){
            model.addAttribute("fileExists",true);
            return "result.html";
        }
        filesService.addFile(fileUpload, user.getUserid());
        model.addAttribute("success",true);
        return "result.html";
    }

    @GetMapping("/files/delete")
    public String deleteNote(@RequestParam("id") int fileid, Model model) {
        if (Objects.nonNull(filesService.getFileById(fileid))) {
            filesService.deleteFile(fileid);
            model.addAttribute("success",true);
            return "result.html";
        }
        model.addAttribute("error",true);
        return "result.html";
    }

}
