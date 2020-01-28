package io.swagger.petstore.ta.model;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Tag {
    TAG_1(1,"Happy hour"),
    TAG_2(2, "nice food"),
    TAG_3(3,"meet with friends!!");
    private final int tagId;
    private final String tagName;
    private final Map<Integer,String> tagMap= Collections.unmodifiableMap(initializeMapping());

    private Tag(Integer i, String s){
        this.tagId = i;
        this.tagName = s;
    }

    private static Map<Integer, String> initializeMapping() {
        Map<Integer, String> tagMap = new HashMap<Integer, String>();
        for (Map.Entry<Integer,String> entry : tagMap.entrySet()) {
            tagMap.put(entry.getKey(), entry.getValue());
        }
        return tagMap;
    }
}
