package com.malak.BoardAPI.Service;

import com.malak.BoardAPI.Repositry.cardRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cardService {
    @Autowired
    cardRepositry cardRepo;

    public void deleteCard(){}

    public void getAllCards(){}
    public void getOneCard(){}

    public void updateCard(){}


}

