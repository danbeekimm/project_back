package data.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import data.dto.JobPostingDto;
import data.dto.ResumeDto;


@Mapper
public interface SearchMapperInter {
	//개발스택string, 지역 string,경력string,기술string
	//한페이지에 보여질 개수 : 9개 int
	//현재페이지 : 1 int
	//
	public List<JobPostingDto> getList(Map<String,Object> map);
	public int getTotalCount ();
	public int getSearchCount (Map<String, Object>map);
	public List<JobPostingDto> getAllDatas(Map<String, Integer>map);
	public List<String> corpname(); 
	public JobPostingDto corp3st(String corp_id);
}
