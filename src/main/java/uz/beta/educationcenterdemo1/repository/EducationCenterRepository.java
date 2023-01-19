package uz.beta.educationcenterdemo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.beta.educationcenterdemo1.entity.EducationCenter;

import java.util.List;
import java.util.UUID;

public interface EducationCenterRepository extends JpaRepository<EducationCenter,UUID> {

    @Query(value = "select * from education_center where name like :educationCenterName", nativeQuery = true)
    List<EducationCenter> findByName(@Param(value = "educationCenterName") String name);

    @Query(value = "select count(*) from subject where subject.education_center_id =:educationCenterId",nativeQuery = true)
    Integer numberOfSubjectsInCenter(UUID educationCenterId);


}
