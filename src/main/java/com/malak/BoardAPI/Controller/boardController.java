package com.malak.BoardAPI.Controller;

import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Service.boardService;
import com.malak.BoardAPI.requestObject.boardRequestObject;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class boardController {
    @Autowired
    boardService boardService;
    @PostMapping("/boards")
public void crateBoard(/*@RequestBody boardRequestObject boardrequestobject*/){
    board  boardRequestedObject = new board();
//    boardRequestedObject.setBoardName(boardrequestobject.getBoardName());
//        boardRequestedObject.setCreatedDate(new Date());
//        boardRequestedObject.setIsActive(true);
//        Map<Integer, String> columns =new HashMap<>();
//        columns.put(1,"To do") ;
//        columns.put(2,"In progress") ;
//        columns.put(3,"Done") ;
//        boardRequestedObject.setColumns(columns);
        boardRequestedObject.setBoardName("malak 1st board");
        boardRequestedObject.setCreatedDate(new Date());
 boardRequestedObject.setIsActive(true);
        boardService.createBoard(boardRequestedObject);
}
@GetMapping("/boards")
    public List<board> getAllBoards(){
        return boardService.getAllBoards();
}




}
