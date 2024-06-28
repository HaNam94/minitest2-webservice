package com.example.minitest2.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Pattern(regexp = "^(0[1-9]|[1-9][0-9])[A-C]-[0-9]{5}$|^(0[1-9]|[1-9][0-9])NG-[0-9]{5}$" , message = "Sai định dạng biển số , vui lòng nhập theo định dạng sau :XX[A,B,C,NG]-XXXXX")
    private String code;
    @NotEmpty(message = "Ten Khong Duoc De Trong")
    private String name;
    @Min(value = 20000 , message = "Gia Khong Duoc Thap Hon 20000")
    private double price;


}
