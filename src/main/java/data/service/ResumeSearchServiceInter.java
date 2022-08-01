package data.service;

import java.util.List;
import java.util.Map;

import data.dto.ResumeDto;

public interface ResumeSearchServiceInter {
	public List<ResumeDto> getList(String job_type, String user_addr, String total_year,String tech_tags,
			int start, int perpage);
	public List<ResumeDto> getAllDatas(int start,int perpage);
	public int getTotalCount ();
	public List<String> jobTypeCnt();
	public int getSearchCount (String job_type, String user_addr, String total_year,String tech_tags);
}
