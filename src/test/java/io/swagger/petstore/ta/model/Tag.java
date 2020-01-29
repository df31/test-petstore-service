package io.swagger.petstore.ta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tag {
    TAG_1("1","Happy hour"),
    TAG_2("2", "nice food"),
    TAG_3("3","meet with friends!!"),
    TAG_4("abc","home alone");

    private final String tagId;
    private final String tagName;
}
