package utill;

import lombok.Data;

@Data
public class Happy {
	private String job_type; // 직종 
	private int experience;// 경력구분
	private String com_addr ;// 근무지
	private String preferred_tech; //우대사항(보유기술)
	private int sort;
	private int currentPage;
}
