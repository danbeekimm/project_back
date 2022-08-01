package data.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dto.JobPostingDto;
import data.dto.ResumeDto;
import data.service.ResumeSearchService;

@RestController
@CrossOrigin
public class ResumeSearchController {

	@Autowired
	private ResumeSearchService resumeSearchService;
	
	@GetMapping("/resumelist")
	public Map<String, Object> getAllList(@RequestParam (defaultValue = "1")int currentPage)
	{
		
		int totalCount;//총갯수
		int perPage=8;//한 페이지당 보여질 글의 갯수
		int perBlock=3;//한(밑에 페이지 숫자)블럭당 보여질 페이지수
		int totalPage; //총페이지수
		int startNum;//한페이지에서 보여질 시작 글번호
		int startPage;//한블럭에서 보여질 시작 페이지 번호
		int endPage;//한블럭에서 보여질 끝 페이지 번호
		int no;//각페이지당 보여질 시작번호
		
		//총글의갯수를 구한다
		totalCount=resumeSearchService.getTotalCount();
		//총페이지수를 구한다
		//밑에두개 같은거임
		totalPage=totalCount/perPage+(totalCount%perPage==0?0:1);
		//totalPage=(int)Math.ceil((double)totalCount/perPage);//무조건올림
		
		//각블럭 시작페이지 (한블럭당 5개일경우)
		//1,6,11,,,(currentPage가 1~5일때는 1,  6~10일땐 6
		startPage=(currentPage-1)/perBlock*perBlock+1;
				
		//5,10,15....(currentPage가 1~5일때는 5,  6~10일땐 10
		endPage=startPage+perBlock-1;
		//문제점 : 마지막블럭은 마지막페이지까지만나와야함 
		if(totalPage<endPage) {
			endPage=totalPage;
		
			
		}
		//각 페이지에서 보여질 글의 시작번호 (mysql 은 0부터)오라클은1부터
		//한페이지당 3개일경우 1페이지:0, 2페이지:3, 3페이지:6;
		startNum=(currentPage-1)*perPage;
		//각 페이지당 보여질 시작번호
		no=totalCount-(currentPage-1)*perPage;
		//데이타 가져오기
		List<ResumeDto> list= resumeSearchService.getAllDatas(startNum, perPage);
		System.out.println("list : "+list);
		
		//출력할페이지번호
		ArrayList<Integer> parr=new ArrayList<>();
		for(int pp=startPage;pp<=endPage;pp++) {
			parr.add(pp);
		}
		//리턴할 map에 필요한변수들넣기
		Map<String, Object> map=new HashMap<>();
		map.put("list", list);
		map.put("parr", parr);
		map.put("totalPage", totalPage);
		map.put("totalCount", totalCount);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("no", no);
		
		return map;
	}
	
	
	@PostMapping("/pagelist")
	
	public Map<String, Object> getPagingList(@RequestBody ResumeDto dto, @RequestParam(defaultValue ="1") int currentPage) //아무것도안주면 1로
	{
		
		int totalCount;//총갯수
		int perPage=8;//한 페이지당 보여질 글의 갯수
		int perBlock=3;//한(밑에 페이지 숫자)블럭당 보여질 페이지수
		int totalPage; //총페이지수
		int startNum;//한페이지에서 보여질 시작 글번호
		int startPage;//한블럭에서 보여질 시작 페이지 번호
		int endPage;//한블럭에서 보여질 끝 페이지 번호
		int no;//각페이지당 보여질 시작번호
		
		//총글의갯수를 구한다
		totalCount=resumeSearchService.getSearchCount(dto.getJob_type(),dto.getUser_addr(),dto.getTotal_year(),dto.getTech_tags());
		//총페이지수를 구한다
		//밑에두개 같은거임
		totalPage=totalCount/perPage+(totalCount%perPage==0?0:1);
		//totalPage=(int)Math.ceil((double)totalCount/perPage);//무조건올림
		
		//각블럭 시작페이지 (한블럭당 5개일경우)
		//1,6,11,,,(currentPage가 1~5일때는 1,  6~10일땐 6
		startPage=(currentPage-1)/perBlock*perBlock+1;
				
		//5,10,15....(currentPage가 1~5일때는 5,  6~10일땐 10
		endPage=startPage+perBlock-1;
		//문제점 : 마지막블럭은 마지막페이지까지만나와야함 
		if(totalPage<endPage) {
			endPage=totalPage;
		
			
		}
		//각 페이지에서 보여질 글의 시작번호 (mysql 은 0부터)오라클은1부터
		//한페이지당 3개일경우 1페이지:0, 2페이지:3, 3페이지:6;
		startNum=(currentPage-1)*perPage;
		//각 페이지당 보여질 시작번호
		no=totalCount-(currentPage-1)*perPage;
		//데이타 가져오기
		
		List<ResumeDto> list= resumeSearchService.getList(dto.getJob_type(),dto.getUser_addr(),dto.getTotal_year(),dto.getTech_tags(), startNum, perPage);
		
		//출력할페이지번호
		ArrayList<Integer> parr=new ArrayList<>();
		for(int pp=startPage;pp<=endPage;pp++) {
			parr.add(pp);
		}
		//리턴할 map에 필요한변수들넣기
		Map<String, Object> map=new HashMap<>();
		map.put("list", list);
		map.put("parr", parr);
		map.put("totalPage", totalPage);
		map.put("totalCount", totalCount);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("no", no);
		
		System.out.println("에러가 날까?" + list);
		
		return map;
	}
	
	@GetMapping("/chart")
	public List<String> jobTypeCnt(){
		List<String>jobslide = new ArrayList<>();
		jobslide = resumeSearchService.jobTypeCnt();
		List<String> slide = new ArrayList<>();
		
		Map<String, Integer> map = new HashMap<String,Integer>();
		for(String str : jobslide) {
			Integer count = map.get(str);
			if (count ==null) {
				map.put(str, 1);
			}else {
				map.put(str, count+1);
			}
		}
		for (String key : map.keySet()) {
			slide.add(key + " : " + map.get(key));
		
	}System.out.println("slide"+slide);
	System.out.println("jobslide"+jobslide);
		
		return slide;
	}
}
