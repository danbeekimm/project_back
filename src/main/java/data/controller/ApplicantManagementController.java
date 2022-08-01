package data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import data.dto.ResumeDto;
import data.mapper.ApplicantManagementMapper;

@RestController
@CrossOrigin
@RequestMapping("/corpManagement")
public class ApplicantManagementController {
    
    @Autowired
    private ApplicantManagementMapper aManagementMapper;

    @GetMapping("/getNewResume")
    public List<ResumeDto> getNewResume(){
        return aManagementMapper.getNewResume();
    }
}
