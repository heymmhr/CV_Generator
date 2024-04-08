package com.cv.cvgenarator.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "basic_information",uniqueConstraints = {
        @UniqueConstraint(name="uk_basicinfo_mobileNumber_email_linkedinUrl",columnNames = {"mobile_number", "email","linkedin_url"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicInformation {

    @Id
    @SequenceGenerator(name = "basic_information_gen",sequenceName = "basic_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "basic_information_gen")
    private Short id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column (name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "background", columnDefinition = "TEXT", nullable = false)
    private String background;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "mobile_number", length = 10, nullable = false)
    private String mobileNumber;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "linkedin_url", length = 200, nullable = false)
    private String linkedinUrl;

    @Column(name = "profile_image", length = 200, nullable = false)
    private String profileImage;

   /* @OneToMany(targetEntity = ExperienceInformation.class, mappedBy = "basicInformation")
    private List<ExperienceInformation> experienceInformationList;*/





}
