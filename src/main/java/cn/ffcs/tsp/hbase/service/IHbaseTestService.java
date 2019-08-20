package cn.ffcs.tsp.hbase.service;

import java.util.List;

import cn.ffcs.tsp.hbase.entity.User;

public interface IHbaseTestService {

	List<User> testHbase();

	Boolean testHbaseSave(User entity);

}
