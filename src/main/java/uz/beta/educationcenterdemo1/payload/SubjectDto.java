package uz.beta.educationcenterdemo1.payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private UUID id;
    private String name;
    private Double price;
    private UUID educationCenterId;

}

