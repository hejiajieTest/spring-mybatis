package cn.ffcs.tsp.hbase.entity;

public class User {

	private String u_name;
	private String u_email;
	private String u_password;
	private String rowKey ;
	
	public User(String name, String email, String password,String rowKey) {
		this.u_name = name;
		this.u_email = email;
		this.u_password = password;
		this.rowKey = rowKey;
	}

	public User() {
	}
	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public String getRowKey() {
		return rowKey;
	}

	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	
}
