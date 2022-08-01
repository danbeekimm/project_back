package data.dto;


import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Alias("resume")
@Data
public class ResumeDto {
    private int resume_idx;
    private String username;
    private String name;
    private String user_photo;
    private String user_birth;
    private String user_gender;
    private String user_email;
    private String user_hp;
    private String addr;
    private String addr_detail;
    private String user_addr;
    private String sch_name;
    private String sch_region;
    private String sch_start;
    private String sch_end;
    private String sch_major;
    private String com_name;
    private String com_start;
    private String com_end;
    private String com_cv;
    private String quit_reason;
    private String com_department;
    private String com_position;
    private String com_year;
    private String com_region;
    private String com_salary;
    private String com_mainwork;
    private String pot_link;
    private String pot_file;
    private String tech_tags;
    private String intro;
    private String res_name;
    private int edu_radio;
    private int exp_radio;
    private int open_radio;
    private String total_year;
    private String job_type;
    
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul" )
    private Timestamp writeday;
}

