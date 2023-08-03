package com.malak.BoardAPI.Models;


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






}
/**/