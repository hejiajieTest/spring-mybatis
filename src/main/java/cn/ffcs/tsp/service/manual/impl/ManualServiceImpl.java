package cn.ffcs.tsp.service.manual.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.ffcs.tsp.entity.ManualInfo;
import cn.ffcs.tsp.mapper.ManualInfoMapper;
//import cn.ffcs.tsp.mapper.ManualInfoMapper;
import cn.ffcs.tsp.mongo.MsgInfo;
import cn.ffcs.tsp.service.manual.IManualService;
import cn.ffcs.tsp.util.Page;
import cn.ffcs.tsp.util.RedisUtil;

@Service 
public class ManualServiceImpl implements IManualService{

    @Autowired
	public ManualInfoMapper manualInfoMapper ;
    
    @Resource
    private MongoTemplate mongoTemplate;
	
	public List<ManualInfo> findAll() {
		List<ManualInfo> list = manualInfoMapper.findAll();
		String json = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
		RedisUtil.set("test", json);
		Object obj = RedisUtil.get("test");
//		RedisUtil.remove("test");
		List<ManualInfo> list1 = new ArrayList<ManualInfo>() ;
		list1 = JSON.parseArray(obj.toString(), ManualInfo.class) ;
		return list1 ;
	}

	@Override
	public ManualInfo findById(String id) {
		return manualInfoMapper.findById(id);
	}

	@Override
	public void save(ManualInfo manualInfo) {
		 manualInfoMapper.save(manualInfo) ;
	}

	@Override
	public void update(ManualInfo manualInfo) {
		manualInfoMapper.update(manualInfo) ;
	}

	@Override
	public List<ManualInfo> findByMap(Map<String, Object> map) {
		return manualInfoMapper.findByMap(map);
	}

	@Override
	public List<ManualInfo> findByPage(Page page, Map<String, Object> map) {
		return manualInfoMapper.findByPage(map);
	}

	@Override
	public void saveList(List<ManualInfo> list) {
		manualInfoMapper.saveList(list);
	}

	@Override
	public List<ManualInfo> findByIds(String[] ids) {
		return manualInfoMapper.findByIds(ids);
	}

	@Override
	public void deleteByIds(List<Long> list) {
		manualInfoMapper.deleteByIds(list);
	}

	@Override
	public void updateByIds(List<String> list) {
		manualInfoMapper.updateByIds(list);
	}

	@Override
	public List<MsgInfo> operateMongo() {
		MsgInfo msgInfo = new MsgInfo();
		msgInfo.setDataTime("");
		msgInfo.setEndDate("");
		msgInfo.setLsh(1);
		msgInfo.setMarketingId("");
		msgInfo.setSearchFilte("1");
		msgInfo.setSendack(null);
		msgInfo.setSendackState("0");
		msgInfo.setSendackTime("");
		msgInfo.setSendmsg("test");
		msgInfo.setSendres(null);
		msgInfo.setSendresState("0");
		msgInfo.setVin("1111111111111");
		msgInfo.setTid("111");
		msgInfo.setType("3");
		//保存操作
		mongoTemplate.save(msgInfo);
		//查询所有
		List<MsgInfo> list = mongoTemplate.findAll(MsgInfo.class);
		//查询器
		Query query = new Query();
		query.addCriteria(Criteria.where("type").is("3")).limit(2).skip(0).with(new Sort(Direction.ASC,"vin"));
		//query.addCriteria(Criteria.where("type").is("3")).limit(pageSize).skip((pageNum-1)*pageSize).with(new Sort(Direction.ASC,"vin"));
		//移除操作
//		mongoTemplate.remove(query, MsgInfo.class);
		//修改器
		Update update = new Update();
		//update.set("vin", "22222222222222");
		//修改多个操作
		//mongoTemplate.updateMulti(query, update, MsgInfo.class);
		//根据条件筛选(分页查询)
		list = mongoTemplate.find(query, MsgInfo.class);
		return list;
	}

}
