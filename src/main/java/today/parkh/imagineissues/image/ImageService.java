package today.parkh.imagineissues.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

@Service
public class ImageService {

    private static String IMAGE_EXTENSION = ".jpg";
    @Value("${image.base.path}")
    private String basePath;

    @Value("${url.basicDomain}")
    private String baseDomain;

    public Resource getImage(String storedName) throws FileNotFoundException {
        String imagePath = basePath + "/" + storedName + IMAGE_EXTENSION;
        File file = new File(imagePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        Resource inputStreamResource = new InputStreamResource(fileInputStream);

        return inputStreamResource;
    }

    public void saveBase64toImage(String fileName, String encodingImage) throws IOException {
        String imagePath = basePath + "/" + fileName + IMAGE_EXTENSION;
        File file = new File(imagePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(Base64.getDecoder().decode(encodingImage.getBytes()));
    }
}
