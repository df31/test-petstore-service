package io.swagger.petstore.ta.model;

import io.swagger.petstore.ta.util.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum PetTypes {
   // DOG_1(201,"Seven",1,"Dog", Arrays.asList("photo_in_twitter", "photo_in_facebook", "photo_in_google_drive"), );

    private final int petId;
    private final String petName;
    private final int petCategoryId;
    private final String petCategoryName;
    private final List<String> photoUrls;
    private final Map<Integer, String> tags;
    private final Status status;
}
