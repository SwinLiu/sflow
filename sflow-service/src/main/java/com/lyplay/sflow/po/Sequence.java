package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Id;

public class Sequence implements Serializable{
	
	private static final long serialVersionUID = 3751197697169195543L;
	
	private String sequenceName;
	private String prefix;
	private Long currValue;
	private String suffix;
	private Integer lpadLength;
	private Integer increment;
	
	public Sequence() {
		// TODO Auto-generated constructor stub
	}
	
	public Sequence(String sequenceName, String prefix, Long currValue,
			String suffix, Integer lpadLength, Integer increment) {
		super();
		this.sequenceName = sequenceName;
		this.prefix = prefix;
		this.currValue = currValue;
		this.suffix = suffix;
		this.lpadLength = lpadLength;
		this.increment = increment;
	}



	@Id
	public String getSequenceName() {
		return sequenceName;
	}
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public Long getCurrValue() {
		return currValue;
	}
	public void setCurrValue(Long currValue) {
		this.currValue = currValue;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Integer getLpadLength() {
		return lpadLength;
	}
	public void setLpadLength(Integer lpadLength) {
		this.lpadLength = lpadLength;
	}
	public Integer getIncrement() {
		return increment;
	}
	public void setIncrement(Integer increment) {
		this.increment = increment;
	}
	@Override
	public String toString() {
		return "Sequence [sequenceName=" + sequenceName + ", prefix=" + prefix
				+ ", currValue=" + currValue + ", suffix=" + suffix
				+ ", lpadLength=" + lpadLength + ", increment=" + increment
				+ "]";
	}
	
	
}
