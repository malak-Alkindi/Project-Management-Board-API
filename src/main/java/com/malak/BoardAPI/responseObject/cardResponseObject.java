package com.malak.BoardAPI.responseObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class cardResponseObject {
    private Long cardId;
    private String title;
    private String description;
    private Integer section;
}
