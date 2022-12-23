package today.parkh.imagineissues;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Base64;

@SpringBootTest
public class ImageTests {
    @Value("${image.base64}")
    String image;

    @Test
    public void base64ToImage() {
    }
}
