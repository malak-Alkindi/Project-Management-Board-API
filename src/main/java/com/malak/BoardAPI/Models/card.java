package com.malak.BoardAPI.Models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@Entity
public class card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long cardId;

    String title;
    String description;
    String section;

    @ManyToOne
    @JoinColumn(name = "board_id")
     board board;

}
