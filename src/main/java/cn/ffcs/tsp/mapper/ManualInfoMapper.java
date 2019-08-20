package cn.ffcs.tsp.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.ffcs.tsp.entity.ManualInfo;

//@Repository
public interface ManualInfoMapper {

	public List<ManualInfo> findAll();
	
	public ManualInfo findById(String id);
	
	public void save(ManualInfo manualInfo);
	
	public void update(ManualInfo manualInfo);

	public List<ManualInfo> findByMap(Map<String,Object> map);

	public List<ManualInfo> findByPage(Map<String, Object> map);

	public void saveList(List<ManualInfo> list);

	public List<ManualInfo> findByIds(String[] ids);

	public void deleteByIds(List<Long> list);

	public void updateByIds(List<String> list);
}
