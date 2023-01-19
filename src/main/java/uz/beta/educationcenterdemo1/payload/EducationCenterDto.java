package uz.beta.educationcenterdemo1.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class EducationCenterDto {
    private UUID id;
    private String name,email,phoneNumber;
    private Integer rank,totalSubject,totalStudents,totalStudentsEntered;

    public void setRank(Integer totalStudents, Integer totalStudentsEntered){
        rank=(int)(((float)totalStudentsEntered/(float)totalStudents)*100);
    }
}
