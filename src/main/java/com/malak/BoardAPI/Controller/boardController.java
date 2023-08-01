package com.malak.BoardAPI.Controller;

import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Service.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class boardController {
    @Autowired
    boardService boardService;

    @GetMapping("api")
    public void someMethodToAddCardToBoard() {
        Long boardId = 1L; // Replace 1L with the actual board ID
        card card = new card();
        card.setTitle("Card Title");
        card.setDescription("Card Description");

        boardService.addCardToBoard(boardId, card);
    }
}
