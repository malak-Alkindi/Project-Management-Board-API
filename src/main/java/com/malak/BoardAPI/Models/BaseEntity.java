package com.malak.BoardAPI.Models;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class BaseEntity {

    Date createdDate;
    Date updatedDate;

}
