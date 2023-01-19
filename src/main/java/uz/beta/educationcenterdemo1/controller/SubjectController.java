package uz.beta.educationcenterdemo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.beta.educationcenterdemo1.payload.ApiResponse;
import uz.beta.educationcenterdemo1.payload.SubjectDto;
import uz.beta.educationcenterdemo1.service.SubjectService;
import uz.beta.educationcenterdemo1.utils.ApplicationConstants;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    final
    SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/saveOrEditSubject")
    public HttpEntity<?> saveOrEditSubject(@RequestBody SubjectDto subjectDto){
        ApiResponse apiResponse = subjectService.saveOrEditSubject(subjectDto);
        return ResponseEntity.status(apiResponse.isSuccess()?
                apiResponse.getMessage().equals("Saved")?201:202:409).
                body(apiResponse);
    }

    @GetMapping("/getAllSubjects")
    public HttpEntity<?> getAllSubjects(@RequestParam(value = "page",
            defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER)Integer page,
                                        @RequestParam(value = "size",
                                        defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE)Integer size){
        return ResponseEntity.ok(subjectService.getAllSubjects(page, size));
    }
}
