package today.parkh.ainimai.image.service.karlo.dto.request;


import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@ToString
@AllArgsConstructor(access = PRIVATE)
@Builder
public class MakeImageRequest {
    String prompt;
    String return_type;

    public static MakeImageRequest of(String prompt) {
        return MakeImageRequest.builder()
                .prompt(prompt)
                .return_type("base64_string")
                .build();
    }
}
