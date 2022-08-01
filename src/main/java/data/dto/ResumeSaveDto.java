package data.dto;

import java.security.Timestamp;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Alias("resume_save")
@Data
public class ResumeSaveDto {
    private int resume_idx;
    private String user_id;
    private String user_name;
    private String user_photo;
    private String user_birthday;
    private String user_gender;
    private String user_email;
    private String user_hp;
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

    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Seoul/Asia" )
    private Timestamp writeday;
}
