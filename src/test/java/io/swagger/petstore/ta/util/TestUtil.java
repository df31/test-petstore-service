package io.swagger.petstore.ta.util;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
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
                .then();
    }

    public static ValidatableResponse findPetByIdRequest(String petStoreUrl, String petId) {
        return given()
                .urlEncodingEnabled(false)
                .baseUri(petStoreUrl)
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .get("/" + petId)
                .then();
    }

    public static JSONObject createPetInfo(int petId,
                                           String petName,
                                           int categoryId,
                                           String categoryName,
                                           List<String> photoUrls,
                                           HashMap<Integer,String> tagMap) {

        JSONObject petContext = new JSONObject();
        JSONObject categoryContext = new JSONObject();

        JSONArray tagsContext = new JSONArray();
        JSONArray photoUrlsContext = new JSONArray();

        categoryContext.put("id", categoryId);
        categoryContext.put("name", categoryName);

        Map<Integer,String> tags = tagMap;
        for (Map.Entry<Integer,String> entry : tags.entrySet()){
            JSONObject tagContext = new JSONObject();
            tagContext.put("id", entry.getKey());
            tagContext.put("name",entry.getValue());
            tagsContext.appendElement(tagContext);
        }

        for (String photoUrl : photoUrls) {
            photoUrlsContext.appendElement(photoUrl);
        }

        petContext.put("id", petId);
        petContext.put("category", categoryContext);
        petContext.put("name", petName);
        petContext.put("photoUrls", photoUrlsContext);
        petContext.put("tags", tagsContext);
        petContext.put("status", Status.available);

        return petContext;
    }
}
