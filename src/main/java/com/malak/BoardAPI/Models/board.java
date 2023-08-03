package com.malak.BoardAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Data
@Entity

public class board  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long board_id;
    String boardName;

//    @ElementCollection
//    @CollectionTable(name = "sections", joinColumns = @JoinColumn(name = "board_id"))
//    @MapKeyColumn(name = "column_id")
//    @Column(name = "column_name")
//    Map<Integer, String> columns;
@JsonIgnore
@OneToMany(mappedBy = "board")
private Set<card> cards;
}
