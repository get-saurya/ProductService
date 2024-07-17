package com.scaler.ProductService.inheritenceTypes.tableperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_students")
public class Student extends User {
    private String batch;
}
