package com.cv.cvgenarator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "fl_district" , uniqueConstraints = {
        @UniqueConstraint(name = "uk_district_name_nameNepali_code", columnNames = {"name","name_n","code"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class District {

    @Id
    @SequenceGenerator(name = "district_gen",sequenceName = "district_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "district_gen")
    private Short id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "name_n", length = 100)
    private String nameNepali;

    @Column(name = "code", length = 10)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "province_id", foreignKey = @ForeignKey(name = "fk_district_province_id"))
    private Province province;

    public District(Short id){
        this.id = id;
    }
   /* @OneToMany(targetEntity = LocalLevel.class, mappedBy = "district")
    private List<LocalLevel> localLevelList;*/
}
