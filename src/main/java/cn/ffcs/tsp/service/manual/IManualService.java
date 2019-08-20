package cn.ffcs.tsp.service.manual;

import java.util.List;
import java.util.Map;

import cn.ffcs.tsp.entity.ManualInfo;
import cn.ffcs.tsp.mongo.MsgInfo;
import cn.ffcs.tsp.util.Page;

public interface IManualService {

	List<ManualInfo> findAll() ;
	
	ManualInfo findById(String id) ;
	
	void save(ManualInfo manualInfo);

	void update(ManualInfo manualInfo);

	List<ManualInfo> findByMap(Map<String, Object> map);

	List<ManualInfo> findByPage(Page page, Map<String, Object> map);

	void saveList(List<ManualInfo> list);

	List<ManualInfo> findByIds(String[] ids);

	void deleteByIds(List<Long> list);

	void updateByIds(List<String> list);

	List<MsgInfo> operateMongo();
}
