package uz.beta.educationcenterdemo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.beta.educationcenterdemo1.payload.ApiResponse;
import uz.beta.educationcenterdemo1.payload.EducationCenterDto;
import uz.beta.educationcenterdemo1.service.EducationCenterService;
import uz.beta.educationcenterdemo1.utils.ApplicationConstants;

import java.util.UUID;

@RestController
@RequestMapping("/api/educationCenter")
public class EducationCenterController {

    final
    EducationCenterService educationCenterService;

    @Autowired
    public EducationCenterController(EducationCenterService educationCenterService) {
        this.educationCenterService = educationCenterService;
    }

    @PostMapping("/saveOrEditCenter")
    public HttpEntity<?> saveOrEditEducationCenter(@RequestBody EducationCenterDto centerDto) {

        ApiResponse response = educationCenterService.saveOrEditEducationCenter(centerDto);
        return ResponseEntity.status(response.isSuccess() ?
                        response.getMessage().equals("Saved") ? 201 : 202 : 409)
                .body(response);
    }

    @GetMapping("/getAllCenters")
    public HttpEntity<?> getAllEducationCenters(@RequestParam(value = "page",
            defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                                @RequestParam(value = "size",
                                                        defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return ResponseEntity.ok(educationCenterService.getAllEducationCenters(page,size));
    }

    @GetMapping("/getById/{id}")
    public HttpEntity<?> getEducationCenterById(@PathVariable(value = "id") UUID id){
        ApiResponse response = educationCenterService.getEducationCenterById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> removeEducationCenterById(@PathVariable(value = "id")UUID id){
        ApiResponse response = educationCenterService.removeEducationCenterById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
}
