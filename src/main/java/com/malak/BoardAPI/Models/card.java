package com.malak.BoardAPI.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Data
@Entity
public class card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cardId;
    String title;
    String description;
    Integer section;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_board_id", referencedColumnName = "board_id")// This specifies the foreign key column name in the card table
    private board board;

}
/**/