package data.service;


import java.util.List;
import java.util.Map;

import data.dto.JobPostingDto;


public interface SearchServiceInter {

	public List<JobPostingDto> getList(String job_type, String com_addr, int experience, String preferred_tech,
			int sort, int start,int perpage);
//	public JobPostingDto getCorp(String corp_id);//회사아아디로 가져오기
//	//지역 기술스택 개발 경력 
	public int getTotalCount();
	public int getSearchCount (String job_type, String com_addr, int experience, String preferred_tech);
	public List<JobPostingDto> getAllDatas(int start,int perpage,int sort);
	public List<String> corpname(); 
	public JobPostingDto corp3st(String corp_id);
	
}
