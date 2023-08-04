package com.malak.BoardAPI.Repositry;

import com.malak.BoardAPI.Models.card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface cardRepositry extends JpaRepository<card, Long> {

    @Query("SELECT c FROM card c WHERE c.board.id = :boardId")
    List<card> findCardsAssignToBoard(@Param("boardId") Long boardId);

    @Query("SELECT c FROM card c WHERE c.board.id = :boardId AND c.cardId = :cardId")
    card findCardByBoardIdAndCardId(@Param("boardId") Long boardId, @Param("cardId") Long cardId);
}
