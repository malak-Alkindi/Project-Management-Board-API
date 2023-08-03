package com.malak.BoardAPI.Repositry;


import com.malak.BoardAPI.Models.board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface boardRepositry  extends JpaRepository<board, Long> {


}
