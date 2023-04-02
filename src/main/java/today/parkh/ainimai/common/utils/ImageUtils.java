package today.parkh.ainimai.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
public class ImageUtils {

    @Value("${image.base.path}")
    private String basePath;

    public String saveImage(String encodedImage) {
        String storedName = UUID.randomUUID() + ".jpg";
        String imagePath = basePath + "/" + storedName;

        try {
            initFolder();
            FileSystemResource resource = new FileSystemResource(imagePath);
            resource.getOutputStream().write(Base64.getDecoder().decode(encodedImage.getBytes()));
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 저장 실패");
        }

        log.info("[Save] : byte -> {}", storedName);
        return storedName;
    }


    public Resource readImage(String storedName) {
        String imagePath = basePath + "/" + storedName;
        FileSystemResource file = new FileSystemResource(imagePath);

        log.info("[Read] : {}", storedName);
        return file;
    }


    private void initFolder() {
        File folder = new File(basePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
