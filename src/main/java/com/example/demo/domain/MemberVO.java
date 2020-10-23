package com.example.demo.domain;

public class MemberVO {
    private String userId;
    private String password;
    private String name;
    private String email;
    private int birthday;
    private String address;
    private String phone;
    private String sex;
    private String U_REMV_FLAG;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getBirthday() {
		return birthday;
	}
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getU_REMV_FLAG() {
		return U_REMV_FLAG;
	}
	public void setU_REMV_FLAG(String u_REMV_FLAG) {
		U_REMV_FLAG = u_REMV_FLAG;
	}
	
	public String toString() {
		return "userId:"+ userId +
				"password:" + password +
				"name:" + name +
				"email:" + email +
				"birthday:" + birthday +
				"address:" + address +
				"phone:" + phone +
				"sex:" + sex +
				"U_REMV_FLAG:" + U_REMV_FLAG;
	}
}