package uz.beta.educationcenterdemo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.beta.educationcenterdemo1.entity.EducationCenter;
import uz.beta.educationcenterdemo1.entity.Subject;
import uz.beta.educationcenterdemo1.payload.ApiResponse;
import uz.beta.educationcenterdemo1.payload.SubjectDto;
import uz.beta.educationcenterdemo1.repository.EducationCenterRepository;
import uz.beta.educationcenterdemo1.repository.SubjectRepository;
import uz.beta.educationcenterdemo1.utils.CommonUtils;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    final
    EducationCenterRepository educationCenterRepository;
    final
    SubjectRepository subjectRepository;
    @Autowired
    public SubjectService(SubjectRepository subjectRepository, EducationCenterRepository educationCenterRepository) {
        this.subjectRepository = subjectRepository;
        this.educationCenterRepository = educationCenterRepository;
    }

    public SubjectDto getSubjectDtoFromSubject(Subject subject){
        SubjectDto subjectDto=new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setPrice(subject.getPrice());
        subjectDto.setEducationCenterId(subjectRepository.findBySubjectId(subject.getId()));
        return subjectDto;
    }

    public ApiResponse saveOrEditSubject(SubjectDto subjectDto){
        try {
            Subject subject=new Subject();
            if (subjectDto.getId()!=null){
                subject=subjectRepository.findById(subjectDto.getId())
                        .orElseThrow(() -> new IllegalStateException("Subject with this Id not found"));
            }
            if (subjectDto.getEducationCenterId()!=null){
                subject.setEducationCenter(educationCenterRepository.findById(subjectDto.getEducationCenterId()).
                        orElseThrow(() -> new IllegalStateException("Education Center not found")));
            }else {
                throw new IOException("no center id");
            }
            subject.setName(subjectDto.getName());
            subject.setPrice(subjectDto.getPrice());
            subjectRepository.save(subject);
            EducationCenter center=subject.getEducationCenter();
            center.setTotalSubjects(educationCenterRepository.numberOfSubjectsInCenter(subjectDto.getEducationCenterId()));
            educationCenterRepository.save(center);
            return new ApiResponse(subjectDto.getId()!=null?"Edited":"Saved",true);
        }catch (Exception e){
            return new ApiResponse(e.getMessage(),false);
        }
    }
    public ApiResponse getAllSubjects(Integer page,Integer siz){
        Page<Subject> subjectPage = subjectRepository.findAll(CommonUtils.getPageableBySubjectPriceAsc(page, siz));
        return new ApiResponse("Success",
                true,
                subjectPage.getTotalElements(),
                subjectPage.getTotalPages(),
                subjectPage.getContent().stream().map(this::getSubjectDtoFromSubject).collect(Collectors.toList()));
    }
//
//    public ApiResponse getOneDistrictById(Integer id) {
//        try {
//            District district = districtRepository.findById(id).orElseThrow(() -> new IllegalStateException("District with this Id not found"));
//            return new ApiResponse("Student found",
//                    true,
//                    getDistrictDtoFromDistrict(district));
//        }catch (Exception e){
//            return new ApiResponse("District with this Id not found",false);
//        }
//    }
//
//    public ApiResponse removeDistrictById(Integer id) {
//        try {
//            districtRepository.deleteById(id);
//            return new ApiResponse("Deleted",true);
//        }catch (Exception e){
//            return new ApiResponse("Error in deleting process",false);
//        }
//    }
}
