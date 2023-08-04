package com.malak.BoardAPI.Controller;

import com.malak.BoardAPI.Error.CustomDataAccessException;
import com.malak.BoardAPI.Error.CustomDataIntegrityException;
import com.malak.BoardAPI.Error.CustomException;
import com.malak.BoardAPI.Error.NotFoundException;
import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Service.boardService;
import com.malak.BoardAPI.requestObject.boardRequestObject;
import com.malak.BoardAPI.responseObject.APIResponse;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api/boards")
public class boardController {
    @Autowired
    boardService boardService;


    @PostMapping
    public boardResponseObject createAnewBoard(@RequestBody boardRequestObject requstedBoard) throws CustomDataAccessException, CustomDataIntegrityException, CustomException {
        board boardObject = new board();
        boardObject.setBoardName(requstedBoard.getBoardName());
        Map<Integer, String> Columns = new HashMap<>();

        // Add elements to the HashMap
        Columns.put(1, "To do");
        Columns.put(2, "In progress");
        Columns.put(3, "Done");
        boardObject.setColumns(Columns);
        return boardService.createAnewBoard(boardObject);
    }

    @GetMapping(value = {"", "{id}"})

    public ResponseEntity<List<boardResponseObject>> getBoardsOrOneBoard(@PathVariable(required = false) Long id) {
        List<boardResponseObject> boardResponseList = new ArrayList<>();

        try {
            List<board> boards = id != null ? Collections.singletonList(boardService.getBoardById(id)) : boardService.getAllBoards();

            for (board boardObj : boards) {
                boardResponseObject boardResponseObject = new boardResponseObject(boardObj.getBoard_id(), boardObj.getBoardName(), boardObj.getColumns());
                boardResponseList.add(boardResponseObject);
            }

            return ResponseEntity.ok(boardResponseList);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<boardResponseObject> updateBoard(@PathVariable Long id, @RequestBody boardRequestObject updatedProduct) throws NotFoundException {
        board response = boardService.updateBoard(id, updatedProduct);

        if (response != null) {

            return ResponseEntity.ok(new boardResponseObject(response.getBoard_id(), response.getBoardName(), response.getColumns()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse deleteBoard(@PathVariable Long id) throws NotFoundException {


 return boardService.deleteABoard(id);

    }
}