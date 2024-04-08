package com.mdfaysalhossain.SMS.With.Maven.service;

import com.mdfaysalhossain.SMS.With.Maven.model.ImageModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.StorageRepo;
import com.mdfaysalhossain.SMS.With.Maven.util.ImgUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class StorageService {
    @Autowired
    StorageRepo storageRepo;

    public String uploadImage(MultipartFile file) {
        try {
            String uniqFname = RandomStringUtils.randomAlphabetic(5) + file.getOriginalFilename();
            byte[] compressedImageData = ImgUtil.compressImage(file.getBytes());
            Blob imageDataBlob = new SerialBlob(compressedImageData);

            ImageModel imageData = new ImageModel();
            imageData.setName(uniqFname);
            imageData.setType(file.getContentType());
            imageData.setImagedata(imageDataBlob);

            storageRepo.save(imageData);

            System.out.println("File Saved");
            return uniqFname;
        } catch (IOException | SQLException e) {
            e.printStackTrace(); // or handle the exception as per your application's requirements
            return null;
        }
    }
}
