package data.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import data.dto.JobPostingDto;
import data.service.SearchService;
import utill.Happy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


	@RestController
	@CrossOrigin
	public class SearchController {
		@Autowired
		private SearchService service;
	
	@PostMapping("/search")
	public Map<String, Object> search(@RequestBody Happy happy)
	{
		System.out.println("happy"+happy);
		int totalCount;//총갯수
		int perPage=9;//한 페이지당 보여질 글의 갯수
		int perBlock=3;//한(밑에 페이지 숫자)블럭당 보여질 페이지수
		int totalPage; //총페이지수
		int startNum;//한페이지에서 보여질 시작 글번호
		int startPage;//한블럭에서 보여질 시작 페이지 번호
		int endPage;//한블럭에서 보여질 끝 페이지 번호
		int no;//각페이지당 보여질 시작번호
		
		//한페이지당 3개일경우 1페이지:0, 2페이지:3, 3페이지:6;
		startNum=(happy.getCurrentPage()-1)*perPage;
		List<JobPostingDto> list= service.getList(happy.getJob_type(),happy.getCom_addr(),happy.getExperience(),happy.getPreferred_tech(),happy.getSort(), startNum, perPage);
		
		//총글의갯수를 구한다
		totalCount=service.getSearchCount(happy.getJob_type(),happy.getCom_addr(),happy.getExperience(),happy.getPreferred_tech());
		//총페이지수를 구한다
		//밑에두개 같은거임
		totalPage=totalCount/perPage+(totalCount%perPage==0?0:1);
		//totalPage=(int)Math.ceil((double)totalCount/perPage);//무조건올림
		
		//각블럭 시작페이지 (한블럭당 5개일경우)
		//1,6,11,,,(currentPage가 1~5일때는 1,  6~10일땐 6
		startPage=(happy.getCurrentPage()-1)/perBlock*perBlock+1;
				
		//5,10,15....(currentPage가 1~5일때는 5,  6~10일땐 10
		endPage=startPage+perBlock-1;
		//문제점 : 마지막블럭은 마지막페이지까지만나와야함 
		if(totalPage<endPage) {
			endPage=totalPage;
		
			
		}
		//각 페이지에서 보여질 글의 시작번호 (mysql 은 0부터)오라클은1부터
		
		//각 페이지당 보여질 시작번호
		no=totalCount-(happy.getCurrentPage()-1)*perPage;
		//데이타 가져오기
		
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
		System.out.println("addr"+happy.getCom_addr());
		System.out.println("endpage"+endPage);
		return map;
	}
	

	
	@GetMapping("/corp")
	public List<JobPostingDto> corpname(){
		//1. corp_name으로 1위회사2,3 아이디가져오기
		List<String> list = service.corpname();
		String company1 = list.get(0);
		String company2 = list.get(1);
		String company3 = list.get(2);
		
		//JobPostingDto dto= service.corp3st(corp_id);
		
		System.out.println("list"+list);
		System.out.println("listsize"+list.size());
		
		//2.corp3st로 1위회사 최근공고 가져오기
		JobPostingDto corp1 = service.corp3st(company1);
		JobPostingDto corp2 = service.corp3st(company2);
		JobPostingDto corp3 = service.corp3st(company3);
		
		List<JobPostingDto> e = new ArrayList<>();
		e.add(corp1);
		e.add(corp2);
		e.add(corp3);
		System.out.println("e"+e);
		return e;
	}

	
	@GetMapping("/list")
	public Map<String, Object> list(@RequestParam(defaultValue = "1") int currentPage, @RequestParam int sort) {
		
		int totalCount;//총갯수
		int perPage=9;//한 페이지당 보여질 글의 갯수
		int perBlock=3;//한(밑에 페이지 숫자)블럭당 보여질 페이지수
		int totalPage; //총페이지수
		int startNum;//한페이지에서 보여질 시작 글번호
		int startPage;//한블럭에서 보여질 시작 페이지 번호
		int endPage;//한블럭에서 보여질 끝 페이지 번호
		int no;//각페이지당 보여질 시작번호
		
		//총글의갯수를 구한다
		totalCount=service.getTotalCount();
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
		List<JobPostingDto> list= service.getAllDatas(startNum, perPage,sort);
		System.out.println("list : "+list);
		
		//출력할페이지번호
		ArrayList<Integer> parr=new ArrayList<>();
		for(int pp=startPage;pp<=endPage;pp++) {
			parr.add(pp);
		}
		
		
		System.out.println(no+"no");
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
	

	   
	}