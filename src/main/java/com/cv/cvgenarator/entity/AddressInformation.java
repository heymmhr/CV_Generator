package com.cv.cvgenarator.entity;


import com.cv.cvgenarator.enums.AddressType;
import com.cv.cvgenarator.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address_information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressInformation {

    @Id
    @SequenceGenerator(name = "address_information_gen",sequenceName = "address_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_information_gen")
    private Short id;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", length = 100)
    private AddressType addressType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basic_id", foreignKey = @ForeignKey(name = "fk_addressinfo_basicinfo_id"))
    private BasicInformation basicInformation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localbody_id", foreignKey = @ForeignKey (name = "fk_addressinfo_localevel_id"))
    private LocalLevel localLevel;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Province.class)
    @JoinColumn(name = "province_id", foreignKey = @ForeignKey(name = "fk_addressinfo_province_id"))
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "addressinfo_country_id"))
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = District.class)
    @JoinColumn(name = "district_id", foreignKey = @ForeignKey(name = "addressinfo_district_id"))
    private District district;
}
