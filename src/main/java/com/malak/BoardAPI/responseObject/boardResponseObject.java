package com.malak.BoardAPI.responseObject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class boardResponseObject {
    private Long board_id;
    private String boardName;
    private Map<Integer, String> columns;

}
