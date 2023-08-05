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
        board boardObject = new board();
        boardObject.setBoardName(requstedBoard.getBoardName());
        Map<Integer, String> Columns = new HashMap<>();

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
        List<card> cardList=cardService.getAllCards(id);
for(card card:cardList){

    cardService.deleteACard(id,card.getCardId());
}
 return boardService.deleteABoard(id);

    }
}

//quations i need to ask
//1) the exeption catshing should be in service or in controller
//2) is getBoardsOrOneBoard is a good way to write
//3) how to use the bidirectional relationship if hibrnate the class will cuz leak?
//4) assigning data should be where ?controller or services
//5) is extinding a card contrller to service is good approch since i will use a commen method