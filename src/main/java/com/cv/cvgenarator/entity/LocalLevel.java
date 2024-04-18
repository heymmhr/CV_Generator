package com.cv.cvgenarator.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "fl_localevel", uniqueConstraints = {
        @UniqueConstraint(name = "uk_name_nameNepali_code", columnNames = {"name","name_n","code"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LocalLevel {

    @Id
    @SequenceGenerator(name = "localevel_gen",sequenceName = "localevel_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "localevel_gen")
    private Short id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "name_n", length = 100)
    private String nameNepali;

    @Column(name = "code", length = 10)
    private String code;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id", foreignKey = @ForeignKey(name = "fk_localevel_district_id"))
    private District district;

    @Column(name = "total_ward_count")
    private Integer totalWardCount;

    public LocalLevel(Short id){
        this.id = id;
    }


}
