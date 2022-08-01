package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("indiv_user")
@Data
public class IndivUserDto {
           	private String id;
           	private String name;
           	private String pwd;
           	private String hp;
           	private String photo;
           	private String addr;
           	private String birth;
           	private String email;
           	private String gender;
}
