package com.scaler.ProductService.inheritenceTypes.tableperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_mentors")
public class Mentor extends User {
    private String company;
}
