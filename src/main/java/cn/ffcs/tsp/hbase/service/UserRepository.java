package cn.ffcs.tsp.hbase.service;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.ResultsExtractor;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

import cn.ffcs.tsp.hbase.entity.User;

@Repository
public class UserRepository {

	@Autowired
	private HbaseTemplate hbaseTemplate;

	private String tableName = "student";

	public static byte[] CF_INFO = Bytes.toBytes("u");

	private byte[] qUser = Bytes.toBytes("u_user");
	private byte[] qEmail = Bytes.toBytes("u_email");
	private byte[] qPassword = Bytes.toBytes("u_password");

	public List<User> findAll() {
		return hbaseTemplate.find(tableName, "u", new RowMapper<User>() {
			@Override
			public User mapRow(Result result, int rowNum) throws Exception {
				return new User(Bytes.toString(result.getValue(CF_INFO, qUser)), 
							    Bytes.toString(result.getValue(CF_INFO, qEmail)),
							    Bytes.toString(result.getValue(CF_INFO, qPassword)),"");
			}
		});
	}
	
	public User get() {
		return hbaseTemplate.get(tableName, "2", new RowMapper<User>() {
			@Override
			public User mapRow(Result result, int rowNum) throws Exception {
				List<KeyValue> list = result.list();
				String kRow=Bytes.toString(list.get(0).getRow());
				String kValue=Bytes.toString(list.get(0).getValue());
				System.out.println(kRow+","+kValue);
				return null;

			}
		});
	}

	public Boolean save(final User entity) {
        return hbaseTemplate.execute(tableName, new TableCallback<Boolean>() {  
            @SuppressWarnings("deprecation")
			public Boolean doInTable(HTableInterface table) throws Throwable {  
                boolean flag = false;  
                try{ 
                	byte[] rowkey = entity.getRowKey().getBytes();
                	Put put = new Put(rowkey); 
                	//通过属性名称，分解出 列族和列名
                	Field[] fields = entity.getClass().getDeclaredFields();
                	//设置反射允许访问私有变量
                	Field.setAccessible(fields, true);  
                	for(int i = 0 , len = fields.length; i < len; i++){
                		 Field field = fields[i];  
                		String varName = field.getName();
                		if(!"rowKey".equals(varName)){
                			String[] familyAndQual = varName.split("[_]");
                			if(2==familyAndQual.length){
                				 if(field.get(entity) instanceof byte[]){
                					 //数组类型
                					 put.add(Bytes.toBytes(familyAndQual[0]),Bytes.toBytes(familyAndQual[1]), (byte[]) field.get(entity)); 
                				 }else{
                					 //string类型
                					 put.add(Bytes.toBytes(familyAndQual[0]),Bytes.toBytes(familyAndQual[1]), Bytes.toBytes(field.get(entity).toString()) );
                				 }
                				  
                			}
                		}
                	}
                	
                    table.put(put);  
                    flag = true;  
                }catch(Exception e){  
                    e.printStackTrace();  
                }  
                return flag;  
            }  
        }); 
	}

}
