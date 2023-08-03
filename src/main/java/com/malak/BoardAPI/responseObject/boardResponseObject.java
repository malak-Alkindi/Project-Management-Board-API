package com.malak.BoardAPI.responseObject;

import com.malak.BoardAPI.Models.card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class boardResponseObject {
    Long board_id;
    String boardName;
    Map<Integer, String> columns;

}
