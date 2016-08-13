package org.sflow.orm.po;

import java.util.Date;

import javax.persistence.Id;

public class Employee {
	private Integer id;
    private String name;
    private Date joindate;
    private Integer age;
 
    @Id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", joindate="
				+ joindate + ", age=" + age + "]";
	}
    
    
}
