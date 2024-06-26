package com.example.UploadDownload_Es;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("${fileRepoFolder}")
    private String fileRepoFolder;

    public String upload(MultipartFile file) throws IOException {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString()+"."+ext;
        File finalDest = new File(fileRepoFolder + "\\" + newFileName);
        if(finalDest.exists()) throw new IOException("Folder does not exist");
        file.transferTo(finalDest);
        return newFileName;
    }
    public byte[] download(String file) throws IOException {
        File fileFromRepo = new File(fileRepoFolder + "\\"+file);
        if(!fileFromRepo.exists()) throw new IOException("File does not exist");
        return IOUtils.toByteArray(new FileInputStream(fileFromRepo));
    }
}
