package com.malak.BoardAPI.Controller;

import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Service.boardService;
import com.malak.BoardAPI.Service.cardService;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
//for testing
import com.malak.BoardAPI.Repositry.boardRepositry;
import com.malak.BoardAPI.Repositry.cardRepositry;
@RestController
@RequestMapping (path = "/api")
public class cardController {
    @Autowired
   boardService boardservice;

    @Autowired
    cardService cardaservice;
    //for testing
    @Autowired
    boardRepositry boardRepo;
    @Autowired
    cardRepositry cardRepo;


    @GetMapping
    List<card> getSubjects() {
        return cardRepo.findAll();
    }




}
