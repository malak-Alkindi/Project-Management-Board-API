package com.malak.BoardAPI.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Map;


@Getter
@Setter
@Data
@Entity

public class board  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long board_id;
    String boardName;

    @ElementCollection
    @CollectionTable(name = "sections", joinColumns = @JoinColumn(name = "board_id"))
    @MapKeyColumn(name = "section_id")
    @Column(name = "section_name")
    private Map<Integer, String> columns;



}
