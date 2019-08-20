package cn.ffcs.tsp.util;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

	 private static RedisTemplate<String,String> redisTemplate ;
	 
	public static RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	//@Resource(name="redisTemplate")
	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		RedisUtil.redisTemplate = redisTemplate;
	}

	public static void remove(String[] keys)
	  {
	    for (String key : keys)
	      remove(key);
	  }

	  public static void removePattern(String pattern)
	  {
	    Set keys = redisTemplate.keys(pattern);
	    if (keys.size() > 0)
	      redisTemplate.delete(keys);
	  }

	  public static void remove(String key)
	  {
	    if (isExit(key))
	      redisTemplate.delete(key);
	  }

	  public static boolean isExit(String key)
	  {
	    return redisTemplate.hasKey(key).booleanValue();
	  }

	  public static Object get(String key)
	  {
	    Object result = null;
	    ValueOperations operations = redisTemplate.opsForValue();
	    result = operations.get(key);
	    return result;
	  }

	  public static boolean set(String key, Serializable value)
	  {
	    boolean result = false;
	    try {
	      ValueOperations operations = redisTemplate.opsForValue();
	      operations.set(key, value);
	      result = true;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return result;
	  }

	  public static boolean set(String key, Serializable value, Long expireTime)
	  {
	    boolean result = false;
	    try {
	      ValueOperations operations = redisTemplate.opsForValue();
	      operations.set(key, value);
	      redisTemplate.expire(key, expireTime.longValue(), TimeUnit.SECONDS);
	      result = true;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return result;
	  }

	  public static void hmSet(String key, Object hashKey, Object value)
	  {
	    HashOperations hash = redisTemplate.opsForHash();
	    hash.put(key, hashKey, value);
	  }

	  public Object hmGet(String key, Object hashKey)
	  {
	    HashOperations hash = redisTemplate.opsForHash();
	    return hash.get(key, hashKey);
	  }

	  public void lPush(String k, Serializable v)
	  {
	    ListOperations list = redisTemplate.opsForList();
	    list.rightPush(k, v);
	  }

	  public List<Serializable> lRange(String k, long l, long l1)
	  {
	    ListOperations list = redisTemplate.opsForList();
	    return list.range(k, l, l1);
	  }

	  public void add(String key, Serializable value)
	  {
	    SetOperations set = redisTemplate.opsForSet();
	    set.add(key, new Serializable[] { value });
	  }

	  public Set<Serializable> setMembers(String key)
	  {
	    SetOperations set = redisTemplate.opsForSet();
	    return set.members(key);
	  }

	  public void zAdd(String key, Serializable value, double scoure)
	  {
	    ZSetOperations zset = redisTemplate.opsForZSet();
	    zset.add(key, value, scoure);
	  }

	  public Set<Serializable> rangeByScore(String key, double scoure, double scoure1)
	  {
	    ZSetOperations zset = redisTemplate.opsForZSet();
	    return zset.rangeByScore(key, scoure, scoure1);
	  }	 
}
