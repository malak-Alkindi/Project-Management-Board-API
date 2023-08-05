package com.malak.BoardAPI.requestObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class cardRequestObject {
    private String title;
    private String description;
    private Integer section;
}
