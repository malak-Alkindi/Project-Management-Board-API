package com.malak.BoardAPI.Repositry;


import com.malak.BoardAPI.Models.board;
import com.malak.BoardAPI.Models.card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface boardRepositry  extends JpaRepository<board, Long> {

}
