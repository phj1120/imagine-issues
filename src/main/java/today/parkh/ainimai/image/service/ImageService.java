package today.parkh.ainimai.image.service;

import today.parkh.ainimai.comment.dto.Prompt;
import today.parkh.ainimai.post.dto.Image;

public interface ImageService {
    Image makeImage(Prompt prompt);
}