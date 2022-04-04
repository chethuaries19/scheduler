package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Product {

    @Id
    private Integer id;

    private Date productTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductTime(Date productTime) {
        this.productTime = productTime;
    }

    public Integer getId() {
        return id;
    }

    public Date getProductTime() {
        return productTime;
    }
}
