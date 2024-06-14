package com.elm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Food {
    @Id
    private Integer foodId;
    private String foodName;
    private String foodExplain;
    @Column(columnDefinition = "mediumtext")
    private String foodImg;
    private Double foodPrice;
    private String remarks;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="businessId",nullable = false,referencedColumnName = "businessId")
    private Business business;
}
