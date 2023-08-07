package com.malak.BoardAPI.Service;


import com.malak.BoardAPI.Error.CustomDataAccessException;
import com.malak.BoardAPI.Error.CustomDataIntegrityException;
import com.malak.BoardAPI.Error.CustomException;
import com.malak.BoardAPI.Error.NotFoundException;
import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Repositry.boardRepositry;
import com.malak.BoardAPI.requestObject.boardRequestObject;
import com.malak.BoardAPI.responseObject.APIResponse;
import com.malak.BoardAPI.responseObject.boardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class boardService {
    @Autowired
    boardRepositry boardRepo;

    public boardResponseObject createAnewBoard(board boardObject) throws CustomDataIntegrityException, CustomDataAccessException, CustomException {

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




    public List<board> getAllBoards() throws NotFoundException {
        List<board> allBoards = boardRepo.findAll();
        if (allBoards.isEmpty()) {
            throw new NotFoundException("No boards found");
        }
        return allBoards;
    }

    public board getBoardById(Long boardId) throws NotFoundException {

        return boardRepo.findById(boardId).orElseThrow(() -> new NotFoundException("Board not found with the given ID: " + boardId));
    }


    public board updateBoard(Long id, boardRequestObject updatedBoard) throws NotFoundException {
        Optional<board> optionalBoard = boardRepo.findById(id);

        if (optionalBoard.isPresent()) {
            board board = optionalBoard.get();

            board.setBoardName(updatedBoard.getBoardName());
            board.setUpdatedDate(new Date());
            boardRepo.save(board);

            return board;
        } else {
            throw new NotFoundException("Board not found with id: " + id );
    }
}

        public APIResponse deleteABoard(Long id) {
            StringBuilder successMessage = new StringBuilder("Board with ID ").append(id).append(" has been deleted successfully.");
            StringBuilder notFoundMessage = new StringBuilder("No board found with ID: ").append(id);
            try {
                Optional<board> boardOptional = boardRepo.findById(id);

                if (boardOptional.isPresent()) {
                    boardRepo.deleteById(id);

                    return new APIResponse(true,successMessage.toString() );
                } else {

                    return new APIResponse(false, notFoundMessage.toString());
                }
            } catch (EmptyResultDataAccessException e) {

                return new APIResponse(false, notFoundMessage.toString());
            }

    }

}
