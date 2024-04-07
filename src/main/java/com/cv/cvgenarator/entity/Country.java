package com.cv.cvgenarator.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "fl_country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @SequenceGenerator(name = "country_gen",sequenceName = "country_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "country_gen")
    private Short id;

    @Column(name = "name_n", length = 100, nullable = false)
    private String nameNepali;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @OneToMany(targetEntity = Province.class, mappedBy = "country")
    private List<Province> provinceList;
}
