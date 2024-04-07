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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_id", foreignKey = @ForeignKey(name = "fk_address_information_basic_information_id"))
    private BasicInformation basicInformation;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "localbody_id", foreignKey = @ForeignKey (name = "fk_address_information_localevel_id"))
    private LocalLevel localLevel;
}
