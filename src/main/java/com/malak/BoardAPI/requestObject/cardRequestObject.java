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
    String title;
    String description;
    Integer section;
}
