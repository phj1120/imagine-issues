package today.parkh.imagineissues;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import today.parkh.imagineissues.image.ImageService;

import java.io.*;
import java.util.Base64;

@SpringBootTest
public class ImageTests {
    @Value("${image.base64}")
    String image;

    @Value("${image.base.path}")
    private String basePath;

    @Autowired
    ImageService imageService;

    @Test
    public void getImage() throws FileNotFoundException {
        String storedName = "readTest.jpg";
        String imagePath = basePath + "/" + storedName;

        File file = new File(imagePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        Resource inputStreamResource = new InputStreamResource(fileInputStream);

        Assertions.assertThat(inputStreamResource).isNotNull();
    }

    @Test
    public void base64ToImage() throws IOException {
        String fileName = "saveTest.jpg";
        Resource resource = imageService.base64ToImage(fileName, image);
    }
}
