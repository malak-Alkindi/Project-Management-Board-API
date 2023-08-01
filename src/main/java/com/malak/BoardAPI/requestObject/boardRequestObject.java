package com.malak.BoardAPI.requestObject;


import com.malak.BoardAPI.Models.card;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class boardRequestObject {
    String boardName;
    List<card> cards;
}
