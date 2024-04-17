package com.cv.cvgenarator.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "fl_province")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Province {

    @Id
    @SequenceGenerator(name = "province_gen",sequenceName = "province_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "province_gen")
    private Short id;

    @Column(name = "name", length = 100, unique = true)
    private String name;

    @Column(name = "name_n", length = 100, unique = true)
    private String nameNepali;

    @Column(name = "code", length = 10, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_province_country_id"))
    private Country country;

    public Province(Short id){
        this.id = id;
    }
}
