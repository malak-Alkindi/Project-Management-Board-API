package com.malak.BoardAPI.Controller;

import com.malak.BoardAPI.Error.CustomDataAccessException;
import com.malak.BoardAPI.Error.CustomDataIntegrityException;
import com.malak.BoardAPI.Error.CustomException;
import com.malak.BoardAPI.Error.NotFoundException;
import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Service.boardService;
import com.malak.BoardAPI.Service.cardService;
import com.malak.BoardAPI.requestObject.boardRequestObject;
import com.malak.BoardAPI.responseObject.APIResponse;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api/boards")

public  class  boardController {
    @Autowired
    boardService boardService;
    @Autowired
    com.malak.BoardAPI.Service.cardService cardService;

    @PostMapping
    public boardResponseObject createAnewBoard(@RequestBody boardRequestObject requstedBoard) throws CustomDataAccessException, CustomDataIntegrityException, CustomException {
        try {
            board boardObject = new board();
            boardObject.setBoardName(requstedBoard.getBoardName());

            Map<Integer, String> Columns = new HashMap<>();
            Columns.put(1, "To do");
            Columns.put(2, "In progress");
            Columns.put(3, "Done");
            boardObject.setColumns(Columns);

            return boardService.createAnewBoard(boardObject);
        } catch (DataIntegrityViolationException e) {
            throw new CustomDataIntegrityException("Data integrity violation occurred while saving the board.", e);
        } catch (DataAccessException e) {
            throw new CustomDataAccessException("An error occurred while saving the board.", e);
        } catch (Exception e) {
            throw new CustomException("An unexpected error occurred while saving the board.", e);
        }

    }


    @GetMapping
    public ResponseEntity<List<boardResponseObject>> getBoards(@PathVariable(required = false) Long id) {
        List<boardResponseObject> boardResponseList = new ArrayList<>();

        try {
            List<board> boards =  boardService.getAllBoards();

            for (board boardObj : boards) {
                boardResponseObject boardResponseObject = new boardResponseObject(boardObj.getBoard_id(), boardObj.getBoardName(), boardObj.getColumns());
                boardResponseList.add(boardResponseObject);
            }

            return ResponseEntity.ok(boardResponseList);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<boardResponseObject> getOneBoard(@PathVariable(required = false) Long id) {
        try {
            board boardObj =boardService.getBoardById(id);
            boardResponseObject boardResponseList=  new boardResponseObject(boardObj.getBoard_id(), boardObj.getBoardName(), boardObj.getColumns());
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

        try {
            List<card> cardList = cardService.getAllCards(id);

            if (!cardList.isEmpty()) {
                for (card card : cardList) {
                    cardService.deleteACard(id,card.getCardId());
                }
            }

            // Now delete the board
            return boardService.deleteABoard(id);
        } catch (NotFoundException e) {
            return boardService.deleteABoard(id);

        }
    }
}
//quations i need to ask
//1) the exeption catshing should be in service or in controller
//2) is getBoardsOrOneBoard is a good way to write
//3) how to use the bidirectional relationship if hibrnate the class will cuz leak?
//4) assigning data should be where ?controller or services
//5) is extinding a card contrller to service is good approch since i will use a commen method