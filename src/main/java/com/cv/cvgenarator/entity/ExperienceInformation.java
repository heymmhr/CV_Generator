package com.cv.cvgenarator.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "experience_information")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceInformation {

    @Id
    @SequenceGenerator(name = "experience_information_gen",sequenceName = "experience_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "basic_information_gen")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_id", foreignKey = @ForeignKey(name = "fk_experienceinfo_basicinfo_id"))
    private BasicInformation basicInformation;

    @Column(name = "company_name", length = 200, nullable = false)
    private String companyName;

    @Column(name = "company_address", length = 200, nullable = false)
    private String companyAddress;

    @Column(name = "company_contact", length = 50, nullable = false)
    private String companyContact;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "to_present")
    private Boolean toPresent;

    /*@OneToMany(mappedBy = "experienceInformation")
    private List<ProjectInformation> projectInformation;*/
}
