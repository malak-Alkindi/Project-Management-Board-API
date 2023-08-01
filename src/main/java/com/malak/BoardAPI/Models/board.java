package com.malak.BoardAPI.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Entity

public class board  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long boardId;
    String boardName;

    // Define the one-to-many relationship with Card
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
     List<card> cards;
}
