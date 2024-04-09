package com.cv.cvgenarator.entity;

import com.cv.cvgenarator.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_information", uniqueConstraints = {
        @UniqueConstraint(name="uk_projectInfo_projectName_techStack_projectUrl",columnNames = {"project_name", "tech_stack","project_url"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInformation {

    @Id
    @SequenceGenerator(name = "project_information_gen",sequenceName = "project_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "project_information_gen")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "experience_id", foreignKey = @ForeignKey(name = "fk_project_information_experience_information_id"))
    private ExperienceInformation experienceInformation;

    @Column(name = "project_name", length = 100, nullable = false)
    private String projectName;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_status", length = 30, nullable = false)
    private ProjectStatus projectStatus;

    @Column(name = "project_role", length = 50, nullable = false)
    private String projectRole;

    @Column(name = "project_description", columnDefinition = "TEXT")
    private String projectDescription;

    @Column(name = "tech_stack", length = 200, nullable = false)
    private String techStack;

    @Column(name = "project_url", length = 200)
    private String projectUrl;




}
