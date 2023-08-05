package com.malak.BoardAPI.Service;

import com.malak.BoardAPI.Error.CustomDataAccessException;
import com.malak.BoardAPI.Error.CustomDataIntegrityException;
import com.malak.BoardAPI.Error.CustomException;
import com.malak.BoardAPI.Error.NotFoundException;
import com.malak.BoardAPI.Models.card;
import com.malak.BoardAPI.Repositry.cardRepositry;
import com.malak.BoardAPI.requestObject.cardRequestObject;
import com.malak.BoardAPI.responseObject.APIResponse;
import com.malak.BoardAPI.responseObject.cardResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class cardService {
    @Autowired
    cardRepositry cardRepo;

    public cardResponseObject createANewCard(card cardObject) throws CustomDataIntegrityException, CustomDataAccessException, CustomException {
        // Validate cardObject and perform necessary input validation before saving.

        try {
            card savedCard = cardRepo.save(cardObject);

            return new cardResponseObject(savedCard.getCardId(), savedCard.getTitle(), savedCard.getDescription(),savedCard.getSection());
        } catch (DataIntegrityViolationException e) {
            throw new CustomDataIntegrityException("Data integrity violation occurred while saving the card.", e);
        } catch (DataAccessException e) {
            throw new CustomDataAccessException("An error occurred while saving the card.", e);
        } catch (Exception e) {
            throw new CustomException("An unexpected error occurred while saving the card.", e);
        }
    }

    public List<card> getAllCards(Long boardId) throws NotFoundException {
        List<card> allCards = cardRepo.findCardsAssignToBoard(boardId);
        if (allCards.isEmpty()) {
            throw new NotFoundException("No cards found for the specified board.");
        }
        return allCards;
    }


    public card getCardById(Long  boardId , Long cardId) throws NotFoundException {
        card foundCard = cardRepo.findCardByBoardIdAndCardId(cardId, boardId);
        if (foundCard == null) {
            throw new NotFoundException("Card not found with ID: " + cardId + " in board with ID: " + boardId);
        }
        return foundCard;
    }
    public card updateCard(Long boardId, Long id, cardRequestObject updatedCard) throws NotFoundException {


        Optional<card> optionalCard = Optional.ofNullable(cardRepo.findCardByBoardIdAndCardId(boardId,id));

        if (optionalCard.isPresent()) {
            card card = optionalCard.get();

            card.setTitle(updatedCard.getTitle());
            card.setDescription(updatedCard.getDescription());
            card.setSection(updatedCard.getSection());
            card.setUpdatedDate(new Date());
            cardRepo.save(card);

            return card;
        } else {
            throw new NotFoundException("Card not found with id: " + id +" assigned to board " + boardId);
        }
    }

    public APIResponse deleteACard(Long boardId,Long id) {
        try {
            Optional<card> optionalCard = Optional.ofNullable(cardRepo.findCardByBoardIdAndCardId(boardId,id));
            if (optionalCard.isPresent()) {
                cardRepo.deleteById(id);

                return new APIResponse(true, "Card with ID " + id + " has been deleted successfully from board " + boardId);
            } else {
                return new APIResponse(false, "No card found with ID: " + id);
            }
        } catch (EmptyResultDataAccessException e) {
            return new APIResponse(false, "No card found with ID: " + id);
        }
    }

}

