package com.malak.BoardAPI.Service;

import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Repositry.cardRepositry;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class cardService {
    @Autowired
    cardRepositry cardRepo;

    public void createCard(card cardObject){
        cardRepo.save(cardObject);
    }

}

