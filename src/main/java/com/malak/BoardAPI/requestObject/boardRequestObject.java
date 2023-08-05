package com.malak.BoardAPI.requestObject;


import com.malak.BoardAPI.Models.card;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class boardRequestObject {

    private String boardName;

}
