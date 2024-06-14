package com.elm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Slf4j
public class Business {
    @Id
    private Integer businessId;
    private String businessName;
    private String businessAddress;
    private String businessExplain;
    @Column(columnDefinition = "mediumtext")
    private String businessImg;
    private int orderTypeId;
    private Double starPrice = 0.0;
    private Double deliveryPrice = 0.0;
    private String remarks;
    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "business")
    private List<Food> food = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "business",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Orders> order = new ArrayList<>();

}
