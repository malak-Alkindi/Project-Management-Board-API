package com.malak.BoardAPI.Service;


import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Repositry.boardRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class boardService {
    @Autowired
    boardRepositry boardRepo;

    public void createBoard(){}
    public void deleteBoard(){}

    public void getAllBoards(){}
    public void getOneBoard(){}

    public void getAllCardsAssigendToBoards(){}

    public void addCardToBoard(Long boardId, card card) {
        // Find the board by its ID
        board board = boardRepo.findById(boardId).orElse(null);

        if (board != null) {
            // Set the board for the card
            card.setBoard(board);

            // Add the card to the board's cards list
            board.getCards().add(card);

            // Save the changes to the database
            boardRepo.save(board);
        } else {
            // Handle the case when the board with the given ID is not found
            // You can throw an exception, return an error message, etc.
        }
    }
}
