package data.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.ResumeDto;
import data.mapper.ResumeSearchMapper;

@Service
public class ResumeSearchService implements ResumeSearchServiceInter {

	@Autowired
	private ResumeSearchMapper resumeSearchMapper;
	

	@Override
	public List<ResumeDto> getList(String job_type, String user_addr, String total_year,String tech_tags,
			int start, int perpage) {
		System.out.println("검색값?" + job_type);
		System.out.println("검색?" + tech_tags);
		System.out.println("검?" + user_addr);
		System.out.println("?" + total_year);
		Map<String, Object> map = new HashMap<>();
		//job
		String[] jobs = job_type.split(",");
		if (job_type.contains("개발 전체")) {
			for (int i=0; i<3; i++) {
				String key = "job_type"+(i+1);
				map.put(key, "");
			}
		}else {
		for (int i=0; i<jobs.length; i++) {
			String key = "job_type"+(i+1);
			map.put(key, '%'+jobs[i]+'%');
		}
		for (int i=jobs.length; i<3; i++) {
			String key = "job_type"+(i+1);
			map.put(key, "");
		}
		}
		
		//address
		String[] addrs = user_addr.split(",");
		//com_addr = "서울 전체,서울 강남구,서울 강동구" - 문자열
		//addr = ["서울 전체", "서울 강남구", "서울 강동구"] - 배열//addrs[i] 서울 전체 라고 돼있으면 서울 %라고 바꿔주기//add[0] = '서울', add[1] = '전체'
		for (int i=0; i<addrs.length; i++) {
			String key = "user_addr"+(i+1);
			
			String[] add = addrs[i].split(" ");
			if (add.length >=2) {
				if (add[1].equals("전체")) {
				add[1]="%";
				addrs[i] = add[0]+" "+add[1];
				}
			}
			System.out.println("addrs[i]"+addrs[i]);
			map.put(key, addrs[i]);
			System.out.println("addrs[i]"+addrs[i]);
		}
		for (int i=addrs.length; i<5; i++) {
			String key = "user_addr"+(i+1);
			map.put(key, "");
		}
		String[] techs = tech_tags.split(",");
		for (int i=0; i<techs.length; i++) {
			String key = "tech_tags"+(i+1);
			map.put(key, '%'+techs[i]+'%');
		}
		for (int i=techs.length; i<5; i++) {
			String key = "tech_tags"+(i+1);
			map.put(key, "");
		}
		
		//year
		map.put("total_year", total_year);
		
		map.put("start", start);
		map.put("perpage", perpage);
		System.out.println("map"+map);
		return resumeSearchMapper.getList(map);
		
		
	}

	@Override
	public List<ResumeDto> getAllDatas(int start,int perpage) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<>();
		map.put("start", start);
		map.put("perpage", perpage);
		return resumeSearchMapper.getAllDatas(map);
		}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return resumeSearchMapper.getTotalCount();
	}
	
//	for (int i = 0; i < jobtypecnt.size(); i++) {
//	String[] arr=jobtypecnt.get(i).split(",");
//	
//	for (int j = 0; j < arr.length; j++) {
//		jobtypetot.add(arr[j]);
//	}
//}System.out.println(jobtypetot);


	@Override
	public List<String> jobTypeCnt() {
		// TODO Auto-generated method stub
		
		List<String> jobtypecnt = new ArrayList<>();
		jobtypecnt = resumeSearchMapper.jobTypeCnt();
		String jobtypetot = "";
		for (int i = 0; i < jobtypecnt.size(); i++) {
			 if (i!=jobtypecnt.size()-1) {
				jobtypetot += jobtypecnt.get(i).concat(",");
				
			}else {
				jobtypetot += jobtypecnt.get(i);
			}
		}
		List<String> job = new ArrayList<String>();
		String [] jobs = jobtypetot.split(",");
		
		for (int i = 0; i < jobs.length; i++) {
			job.add(jobs[i]);
		}
		System.out.println("job"+job);
		return job;
	}

	@Override
	public int getSearchCount(String job_type, String user_addr, String total_year, String tech_tags) {
Map<String, Object> map = new HashMap<>();
		
		String[] jobs = job_type.split(",");
		if (job_type.contains("개발 전체")) {
			for (int i=0; i<3; i++) {
				String key = "job_type"+(i+1);
				map.put(key, "");
			}
		}else {
		for (int i=0; i<jobs.length; i++) {
			String key = "job_type"+(i+1);
			map.put(key, '%'+jobs[i]+'%');
		}
		for (int i=jobs.length; i<3; i++) {
			String key = "job_type"+(i+1);
			map.put(key, "");
		}
		}
		//address
		String[] addrs = user_addr.split(",");
		//com_addr = "서울 전체,서울 강남구,서울 강동구" - 문자열
		//addr = ["서울 전체", "서울 강남구", "서울 강동구"] - 배열//addrs[i] 서울 전체 라고 돼있으면 서울 %라고 바꿔주기//add[0] = '서울', add[1] = '전체'
		for (int i=0; i<addrs.length; i++) {
			String key = "user_addr"+(i+1);
			
			String[] add = addrs[i].split(" ");
			if (add.length >=2) {
				if (add[1].equals("전체")) {
				add[1]="%";
				addrs[i] = add[0]+" "+add[1];
				}
			}
			System.out.println("addrs[i]"+addrs[i]);
			map.put(key, addrs[i]);
			System.out.println("addrs[i]"+addrs[i]);
		}
		for (int i=addrs.length; i<5; i++) {
			String key = "user_addr"+(i+1);
			map.put(key, "");
		}
		
		String[] techs = tech_tags.split(",");
		for (int i=0; i<techs.length; i++) {
			String key = "tech_tags"+(i+1);
			map.put(key, '%'+techs[i]+'%');
		}
		for (int i=techs.length; i<5; i++) {
			String key = "tech_tags"+(i+1);
			map.put(key, "");
		}
		
		//experience
		map.put("total_year", total_year);
		//perpage
		return resumeSearchMapper.getSearchCount(map);
	
	}
}
