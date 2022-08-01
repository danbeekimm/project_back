package data.dto;


import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("notice")
public class JobPostingDto {
	private int num;
	private String corp_id; //회사아이디
	private String corp_name;//회사이름
	private String title;//채용제목
	private int experience;// 경력구분 
	private String education ;// 최종학력
	private String salary ;// 급여조건
	private String position ;// 채용직급
	private String job_type; // 직종 
	private String hire_type ;//고용형태 
	private int hire_num ;//고용인원
	private String preferred_tech; //우대사항(보유기술)
	private String main_work ;// 주요업무 3333
	private String com_addr ;// 근무지
	private String end_date; //마감일
	private String job_posting_photo;// 공고 이미지 
	private int view_cnt; // 조회수
	private Timestamp writeday ;//게시일
}
