package cn.ffcs.tsp.hbase.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.tsp.hbase.entity.User;
import cn.ffcs.tsp.hbase.service.IHbaseTestService;
import cn.ffcs.tsp.hbase.service.UserRepository;

@Service
public class HbaseTestServiceImpl implements IHbaseTestService{

	@Resource
	private UserRepository userRepository ;
	
	@Override
	public List<User> testHbase() {
		List<User> list = userRepository.findAll() ;
		return list;
	}

	@Override
	public Boolean testHbaseSave(User entity) {
		return userRepository.save(entity);
	}

}
