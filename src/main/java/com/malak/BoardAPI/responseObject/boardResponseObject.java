package com.malak.BoardAPI.responseObject;

import com.malak.BoardAPI.Models.card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class boardResponseObject {
    Long boardId;
    String boardName;

}
