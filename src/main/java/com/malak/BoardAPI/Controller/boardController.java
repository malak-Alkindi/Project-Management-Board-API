package com.malak.BoardAPI.Controller;

import com.malak.BoardAPI.Error.CustomDataAccessException;
import com.malak.BoardAPI.Error.CustomDataIntegrityException;
import com.malak.BoardAPI.Error.CustomException;
import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Service.boardService;
import com.malak.BoardAPI.requestObject.boardRequestObject;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/boards")
public class boardController {
    @Autowired
    boardService boardService;


    @PostMapping
    public boardResponseObject createAnewBoard(@RequestBody boardRequestObject requstedBoard) throws CustomDataAccessException, CustomDataIntegrityException, CustomException {
        board boardObject= new board();
        boardObject.setBoardName(requstedBoard.getBoardName());
        Map<Integer, String> Columns = new HashMap<>();

        // Add elements to the HashMap
        Columns.put(1, "To do");
        Columns.put(2, "In progress");
        Columns.put(3, "Done");
        boardObject.setColumns(Columns);
        return boardService.createAnewBoard(boardObject);
    }
}
