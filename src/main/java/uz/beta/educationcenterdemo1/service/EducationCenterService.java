package uz.beta.educationcenterdemo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.beta.educationcenterdemo1.entity.EducationCenter;
import uz.beta.educationcenterdemo1.payload.ApiResponse;
import uz.beta.educationcenterdemo1.payload.EducationCenterDto;
import uz.beta.educationcenterdemo1.repository.EducationCenterRepository;
import uz.beta.educationcenterdemo1.utils.CommonUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EducationCenterService {

    final
    EducationCenterRepository educationCenterRepository;

    @Autowired
    public EducationCenterService(EducationCenterRepository educationCenterRepository) {
        this.educationCenterRepository = educationCenterRepository;
    }

    public EducationCenterDto getEducationCenterDtoFromEducationCenter(EducationCenter center){
        EducationCenterDto centerDto=new EducationCenterDto();
        centerDto.setId(center.getId());
        centerDto.setName(center.getName());
        centerDto.setEmail(center.getEmail());
        centerDto.setPhoneNumber(center.getPhoneNumber());
        centerDto.setRank(center.getTotalStudents(),center.getTotalStudentsEntered());
        centerDto.setTotalStudents(center.getTotalStudents());
        centerDto.setTotalStudentsEntered(center.getTotalStudentsEntered());
        centerDto.setTotalSubject(center.getTotalSubjects());
        return centerDto;
    }


    public ApiResponse saveOrEditEducationCenter(EducationCenterDto dto) {
        try {
            EducationCenter center=new EducationCenter();
            if (dto.getId()!=null){
                center=educationCenterRepository.findById(dto.getId())
                        .orElseThrow(() -> new IllegalStateException("Education center with" +
                                "this Id not found"));
            }
            center.setName(dto.getName());
            center.setEmail(dto.getEmail());
            center.setPhoneNumber(dto.getPhoneNumber());
            center.setTotalStudents(dto.getTotalStudents());
            center.setTotalStudentsEntered(dto.getTotalStudentsEntered());
            center.setRank(center.getTotalStudents(),center.getTotalStudentsEntered());
            educationCenterRepository.save(center);
            return new ApiResponse(dto.getId()!=null?"Edited":"Saved",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse getAllEducationCenters(Integer page, Integer size) {
        Page<EducationCenter> centerPage = educationCenterRepository.findAll(CommonUtils.getPageableByRankDesc(page, size));
        return new ApiResponse("Success",
                true,
                centerPage.getTotalElements(),
                centerPage.getTotalPages(),
                centerPage.getContent().stream().map(this::getEducationCenterDtoFromEducationCenter).collect(Collectors.toList()));
    }

    public ApiResponse getEducationCenterById(UUID id) {
        try{
            EducationCenter center = educationCenterRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException("Education Center with this Id not found"));
            return new ApiResponse("Education Center found",
                    true,
                    getEducationCenterDtoFromEducationCenter(center));

        }catch (Exception e){
            return new ApiResponse("Education Center with this Id not found",false);
        }
    }

    public ApiResponse removeEducationCenterById(UUID id) {
        try {
            educationCenterRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error in deleting process",false);
        }
    }
}
