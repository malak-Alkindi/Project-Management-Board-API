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
import com.malak.BoardAPI.requestObject.cardRequestObject;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import com.malak.BoardAPI.responseObject.cardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
//for testing
import com.malak.BoardAPI.Repositry.boardRepositry;
import com.malak.BoardAPI.Repositry.cardRepositry;
@RestController
@RequestMapping ("/api/boards/{board_id}/cards")
public class cardController {
    @Autowired
    boardService boardService;
    @Autowired
    cardService cardService;

    @PostMapping
    public cardResponseObject createANewCard(@PathVariable Long board_id,@RequestBody cardRequestObject requestedCard) throws CustomDataAccessException, CustomDataIntegrityException, CustomException, NotFoundException {

        board boardObj = boardService.getBoardById(board_id);
        card cardObject = new card();
        cardObject.setTitle(requestedCard.getTitle());
        cardObject.setSection(requestedCard.getSection());
        cardObject.setDescription(requestedCard.getDescription());
        cardObject.setBoard(boardObj);
        cardObject.setCreatedDate(new Date());

        return cardService.createANewCard(cardObject);
    }




@GetMapping
    public  List<cardResponseObject> getAllCards(@PathVariable Long board_id) throws NotFoundException {
    List<cardResponseObject> cardResponseList = new ArrayList<>();

    for (card cardObj : cardService.getAllCards(board_id)) {
        cardResponseObject cardResponseObject = new cardResponseObject(cardObj.getCardId(),cardObj.getTitle(),cardObj.getDescription(),cardObj.getSection());
        cardResponseList.add(cardResponseObject);
    }
        return cardResponseList;
}

    @GetMapping("{id}")
    public  cardResponseObject getOneCard(@PathVariable Long board_id,@PathVariable Long id) throws NotFoundException {
        card cardObj = cardService.getCardById(id,board_id);


        return new cardResponseObject(cardObj.getCardId(),cardObj.getTitle(),cardObj.getDescription(),cardObj.getSection());
    }
}
