package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.ResumeDto;

@Mapper
public interface ApplicantManagementMapper {
    public List<ResumeDto> getNewResume();
}
