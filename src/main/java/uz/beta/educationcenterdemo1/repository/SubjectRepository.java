package uz.beta.educationcenterdemo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.beta.educationcenterdemo1.entity.Subject;

import java.util.UUID;


public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    @Query(value = "select CAST(education_center_id as varchar) education_center_id from subject where subject.id =:id",nativeQuery = true)
    UUID findBySubjectId(UUID id);

//    @Query(value = "select education_center_id from subject where subject.id =:subjectId",nativeQuery = true)
//    UUID getCenterId( UUID subjectId);
}
