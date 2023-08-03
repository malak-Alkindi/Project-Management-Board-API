package com.malak.BoardAPI.Service;


import com.malak.BoardAPI.Error.CustomDataAccessException;
import com.malak.BoardAPI.Error.CustomDataIntegrityException;
import com.malak.BoardAPI.Error.CustomException;
import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Repositry.boardRepositry;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class boardService {
    @Autowired
    boardRepositry boardRepo;

    public boardResponseObject createAnewBoard(board boardObject) throws CustomDataIntegrityException, CustomDataAccessException, CustomException {



        // Validate boardObject and perform necessary input validation before saving.

        try {
            board savedBoard = boardRepo.save(boardObject);

            return new boardResponseObject(savedBoard.getBoard_id(),savedBoard.getBoardName(),savedBoard.getColumns());
        } catch (DataIntegrityViolationException e) {
            throw new CustomDataIntegrityException("Data integrity violation occurred while saving the board.", e);
        } catch (DataAccessException e) {
            throw new CustomDataAccessException("An error occurred while saving the board.", e);
        } catch (Exception e) {
            throw new CustomException("An unexpected error occurred while saving the board.", e);
        }
    }




    public List<board> getAllBoards(Long boardId) {
        if (boardId != null) {
            Optional<board> boardOptional = boardRepo.findById(boardId);
            return boardOptional.map(List::of).orElseGet(List::of);
        } else {
            return boardRepo.findAll();
        }
    }
}
