package uz.beta.educationcenterdemo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import uz.beta.educationcenterdemo1.entity.AbsEntity.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String contentType;

    private int size;

    private String path;

    @ManyToOne
    private EducationCenter educationCenter;

    public Attachment(String name, String contentType, int size) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
    }

}
