package io.swagger.petstore.ta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static io.swagger.petstore.ta.model.Tag.*;

@Getter
@AllArgsConstructor
public enum PetTypes {
    DOG("201", "Seven", "1", "Dog", Arrays.asList("photo_in_twitter", "photo_in_facebook", "photo_in_google_drive"), Arrays.asList(TAG_1), PetStatus.available),
    CAT("101", "MiMi", "2", "Cat", Arrays.asList("facbook_pjhoto_url", "tencent_space_url"), Arrays.asList(TAG_1,TAG_2), PetStatus.pending),
    FISH("301", "Discus", "3", "Fish", Arrays.asList("twitter_photo_link", "OneDrive_picture_link", "photo_link_in_cloud", "other_pic_link"), Arrays.asList(TAG_1,TAG_2,TAG_3), PetStatus.sold),
    PET_HAS_INVALID_ID("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", "Red Terqus", "3", "Fish", Arrays.asList("twitter_photo_link", "OneDrive_picture_link", "photo_link_in_cloud", "other_pic_link"), Arrays.asList(TAG_3), PetStatus.sold);

    private final String petId;
    private final String petName;
    private final String petCategoryId;
    private final String petCategoryName;
    private final List<String> photoUrls;
    private final List<Tag> tags;
    private final PetStatus petStatus;
}
