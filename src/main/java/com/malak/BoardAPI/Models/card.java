package com.malak.BoardAPI.Models;


import jakarta.persistence.*;
import lombok.*;




@Getter
@Setter
@Entity
public class card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String title;
    private String description;
    private  Integer section;


    @ManyToOne
    @JoinColumn(name = "board_id_fk", referencedColumnName = "board_id")
    private board board;



}
/**/