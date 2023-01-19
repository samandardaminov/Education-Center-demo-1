package uz.beta.educationcenterdemo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.beta.educationcenterdemo1.entity.AbsEntity.AbsNameEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EducationCenter extends AbsNameEntity {

    @Email
    private String email;

    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$", message = "Phone number must be 13 digits.")
    private String phoneNumber;

    private Integer totalSubjects;

    private Integer rank;

    private Integer totalStudents;

    private Integer totalStudentsEntered;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "educationCenter",cascade = CascadeType.ALL)
    private List<Subject> subjects;


    public void setRank(Integer totalStudents, Integer totalStudentsEntered){
        rank=(int)(((float)totalStudentsEntered/(float)totalStudents)*100);
    }

}
