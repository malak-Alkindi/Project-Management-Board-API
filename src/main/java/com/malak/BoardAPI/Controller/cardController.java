package com.malak.BoardAPI.Controller;

import com.malak.BoardAPI.Error.CustomDataAccessException;
import com.malak.BoardAPI.Error.CustomDataIntegrityException;
import com.malak.BoardAPI.Error.CustomException;
import com.malak.BoardAPI.Error.NotFoundException;
import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Service.boardService;
import com.malak.BoardAPI.Service.cardService;
import com.malak.BoardAPI.requestObject.cardRequestObject;
import com.malak.BoardAPI.responseObject.APIResponse;
import com.malak.BoardAPI.responseObject.cardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<cardResponseObject> getAllCards(@PathVariable Long board_id) throws NotFoundException {
        List<cardResponseObject> cardResponseList = new ArrayList<>();

        List<card> allCards = cardService.getAllCards(board_id);
        for (card cardObj : allCards) {
            cardResponseObject cardResponseObject = new cardResponseObject(cardObj.getCardId(), cardObj.getTitle(), cardObj.getDescription(), cardObj.getSection());
            cardResponseList.add(cardResponseObject);
        }

        return cardResponseList;
    }
    @GetMapping("{id}")
    public cardResponseObject getOneCard(@PathVariable Long board_id, @PathVariable Long id) {
        try {
            card cardObj = cardService.getCardById(id, board_id);
            return new cardResponseObject(cardObj.getCardId(), cardObj.getTitle(), cardObj.getDescription(), cardObj.getSection());
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<cardResponseObject> updateCard(@PathVariable Long board_id,@PathVariable Long id, @RequestBody cardRequestObject updatedCard) throws NotFoundException {

        card response = cardService.updateCard(board_id,id, updatedCard);

        if (response != null) {
            return ResponseEntity.ok(new cardResponseObject(response.getCardId(), response.getTitle(), response.getDescription(), response.getSection()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse deleteCard(@PathVariable Long board_id,@PathVariable Long id) throws NotFoundException {

        return cardService.deleteACard(board_id,id);
    }
}
