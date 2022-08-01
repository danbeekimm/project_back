package data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.JobPostingDto;
import data.mapper.SearchMapperInter;
@Service
public class SearchService implements SearchServiceInter {

@Autowired
	private SearchMapperInter mapper;
	@Override
	public List<JobPostingDto> getList(String job_type, String com_addr, int experience, String preferred_tech,
			int sort, int start, int perpage) {
		System.out.println(job_type);
		Map<String, Object> map = new HashMap<>();
		
		//job_type 이 한개나 두개만들어오면 남은부분은 ''으로 대신 채우기
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
		String[] addrs = com_addr.split(",");
		//com_addr = "서울 전체,서울 강남구,서울 강동구" - 문자열
		//addr = ["서울 전체", "서울 강남구", "서울 강동구"] - 배열//addrs[i] 서울 전체 라고 돼있으면 서울 %라고 바꿔주기//add[0] = '서울', add[1] = '전체'
		for (int i=0; i<addrs.length; i++) {
			String key = "com_addr"+(i+1);
			
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
			String key = "com_addr"+(i+1);
			map.put(key, "");
		}
		
		//tech
		String[] techs = preferred_tech.split(",");
		for (int i=0; i<techs.length; i++) {
			String key = "preferred_tech"+(i+1);
			map.put(key, '%'+techs[i]+'%');
		}
		for (int i=techs.length; i<5; i++) {
			String key = "preferred_tech"+(i+1);
			map.put(key, "");
		}
		
		//experience
		map.put("experience", experience);
		//perpage
		map.put("start", start);
		map.put("perpage", perpage);
		map.put("sort", sort);
		System.out.println("2222"+map);
		return mapper.getList(map) ;
		
	}


	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return mapper.getTotalCount();
	}


	@Override
	public List<JobPostingDto> getAllDatas(int start, int perpage,int sort) {
		Map<String, Integer> map = new HashMap<>();
		map.put("start", start);
		map.put("perpage", perpage);
		map.put("sort", sort);
		System.out.println("1111"+map);
		return mapper.getAllDatas(map);
	}


	@Override
	public List<String> corpname() {
		// TODO Auto-generated method stub
		return mapper.corpname();
	}


	@Override
	public JobPostingDto corp3st(String corp_id) {
		// TODO Auto-generated method stub
		return mapper.corp3st(corp_id);
	}


	@Override
	public int getSearchCount(String job_type, String com_addr, int experience, String preferred_tech) {
		// TODO Auto-generated method stub
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
		String[] addrs = com_addr.split(",");
		//com_addr = "서울 전체,서울 강남구,서울 강동구" - 문자열
		//addr = ["서울 전체", "서울 강남구", "서울 강동구"] - 배열//addrs[i] 서울 전체 라고 돼있으면 서울 %라고 바꿔주기//add[0] = '서울', add[1] = '전체'
		for (int i=0; i<addrs.length; i++) {
			String key = "com_addr"+(i+1);
			
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
			String key = "com_addr"+(i+1);
			map.put(key, "");
		}
		
		//tech
				String[] techs = preferred_tech.split(",");
				for (int i=0; i<techs.length; i++) {
					String key = "preferred_tech"+(i+1);
					map.put(key, '%'+techs[i]+'%');
				}
				for (int i=techs.length; i<5; i++) {
					String key = "preferred_tech"+(i+1);
					map.put(key, "");
				}
				
		//experience
		map.put("experience", experience);
		//perpage
		return mapper.getSearchCount(map);
	}


}
