package io.swagger.petstore.ta.util;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.ta.model.PetStatus;
import io.swagger.petstore.ta.model.PetTypes;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@UtilityClass
public class TestUtil {

    public ScenarioState state;

    static {
        state = new ScenarioState();
    }

    public static ValidatableResponse addPetRequest(String petStoreUrl, String jsonBody) {
        return given()
                .urlEncodingEnabled(false)
                .baseUri(petStoreUrl)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .log().all()
                .post()
                .then()
                .log().all();
    }

    public static ValidatableResponse findPetByIdRequest(String petStoreUrl, String petId) {
        return given()
                .urlEncodingEnabled(false)
                .baseUri(petStoreUrl)
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .get("/" + petId)
                .then()
                .log().all();
    }

    public static JSONObject createPetInfo(PetTypes pet) {

        JSONObject petContext = new JSONObject();
        JSONObject categoryContext = new JSONObject();

        JSONArray tagsContext = new JSONArray();
        JSONArray photoUrlsContext = new JSONArray();

        categoryContext.put("id", pet.getPetCategoryId());
        categoryContext.put("name", pet.getPetCategoryName());

        Map<Integer,String> tags = pet.getTags();
        for (Map.Entry<Integer,String> entry : tags.entrySet()){
            JSONObject tagContext = new JSONObject();
            tagContext.put("id", entry.getKey());
            tagContext.put("name",entry.getValue());
            tagsContext.appendElement(tagContext);
        }

        for (String photoUrl : pet.getPhotoUrls()) {
            photoUrlsContext.appendElement(photoUrl);
        }

        petContext.put("id", pet.getPetId());
        petContext.put("category", categoryContext);
        petContext.put("name", pet.getPetName());
        petContext.put("photoUrls", photoUrlsContext);
        petContext.put("tags", tagsContext);
        petContext.put("status", pet.getPetStatus());

        return petContext;
    }
}
