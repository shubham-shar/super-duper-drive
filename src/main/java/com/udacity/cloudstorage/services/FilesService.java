package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.FileMapper;
import com.udacity.cloudstorage.model.Files;
import com.udacity.cloudstorage.model.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Shubham Sharma
 * @date 8/4/20
 */
@Service
public class FilesService {

    @Autowired
    private FileMapper filesMapper;

    public List<FileDto> getAllFiles(int userid){
        List<Files> files = filesMapper.findByUserId(userid);
        if (Objects.isNull(files)) {
            return Collections.emptyList();
        }
        return files.stream().map(this::getFileDto).collect(Collectors.toList());
    }

    public Files getFileById(int id){
        return filesMapper.findById(id);
    }

    public void addFile(MultipartFile fileUpload, int userid) throws IOException {
        try {
            Files file = Files.builder()
                    .contenttype(fileUpload.getContentType())
                    .filedata(fileUpload.getBytes())
                    .filename(fileUpload.getOriginalFilename())
                    .filesize(Long.toString(fileUpload.getSize()))
                    .build();
            filesMapper.insertFile(file, userid);
        } catch (IOException e) {
            throw e;
        }

    }

    public void deleteFile(int fileid) {
        filesMapper.deleteFile(fileid);
    }

    public FileDto getFileDto(Files file) {
        String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
        String dataURL = "data:" + file.getContenttype() + ";base64," + base64;
        return FileDto.builder()
                .id(file.getFileid())
                .name(file.getFilename())
                .dataURL(dataURL)
                .build();
    }

    public Boolean isFileAdded(String fileName){
        return Optional.ofNullable(filesMapper.findByFileName(fileName)).isPresent();
    }
}
