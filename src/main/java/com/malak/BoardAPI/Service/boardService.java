package com.malak.BoardAPI.Service;


import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Repositry.boardRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class boardService {
    @Autowired
    boardRepositry boardRepo;

  public board createBoard(board boardObject){
      return boardRepo.save(boardObject);
  }

    public List<board> getAllBoards(){
        return boardRepo.findAll();
    }
}
