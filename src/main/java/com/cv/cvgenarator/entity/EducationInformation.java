package com.cv.cvgenarator.entity;


import com.cv.cvgenarator.enums.DegreeName;
import com.cv.cvgenarator.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "education_information",uniqueConstraints = {
        @UniqueConstraint(name = "unique_education_information_institution_contact", columnNames = "institution_contact")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationInformation {


    @Id
    @SequenceGenerator(name = "education_information_gen",sequenceName = "education_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "education_information_gen")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_id", foreignKey = @ForeignKey(name = "fk_education_information_basic_information_id"))
    private BasicInformation basicInformation;

    @Column(name = "institution_name", length = 200, nullable = false)
    private String institutionName;

    @Column(name = "institution_address", length = 200, nullable = false)
    private String institutionAddress;

    @Column(name = "institution_contact", length = 50, nullable = false, unique = true)
    private String institutionContact;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "to_present")
    private Boolean toPresent;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_name", length = 100, nullable = false)
    private DegreeName degreeName;

    @Column(name = "education_description", length = 100, nullable = false)
    private String educationDescription;
}
