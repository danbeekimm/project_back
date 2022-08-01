package data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import data.dto.ResumeDto;

@Mapper
public interface ResumeSearchMapper {
	public List<ResumeDto> getList(Map<String, Object>map);
	public List<ResumeDto> getAllDatas(Map<String, Integer>map);
	public int getTotalCount ();
	public List<String> jobTypeCnt();
	public int getSearchCount (Map<String, Object>map);
}
