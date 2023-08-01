package com.malak.BoardAPI.Repositry;

import com.malak.BoardAPI.Models.card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cardRepositry extends JpaRepository<card, Long> {
}
