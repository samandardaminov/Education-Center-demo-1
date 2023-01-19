package uz.beta.educationcenterdemo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.beta.educationcenterdemo1.entity.AbsEntity.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject extends AbsNameEntity {
    private Double price;

    @ManyToOne
    private EducationCenter educationCenter;
}
