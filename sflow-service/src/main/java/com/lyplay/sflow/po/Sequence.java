package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.lyplay.sflow.common.util.DateUtil;

@Table(name = "sflow_sequence")
public class Sequence implements Serializable {

	private static final long serialVersionUID = 3751197697169195543L;

	@Id
	@Column(length = 20)
	private String sequenceName;

	@Column(length = 20)
	private String prefix;

	@Column(length = 20, unique = true, nullable = false)
	private Long currValue;

	@Column(length = 20)
	private String suffix;

	@Column(length = 3, nullable = false)
	private Integer lpadLength;
	
	@Column(length = 1, nullable = false)
	private char lpadChar;

	@Column(length = 3, nullable = false)
	private Integer increment;

	public Sequence() {
	}

	public Sequence(String sequenceName, String prefix, Long currValue,
			String suffix, Integer lpadLength, char lpadChar, Integer increment) {
		super();
		this.sequenceName = sequenceName;
		this.prefix = prefix;
		this.currValue = currValue;
		this.suffix = suffix;
		this.lpadLength = lpadLength;
		this.lpadChar = lpadChar;
		this.increment = increment;
	}

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

	public char getLpadChar() {
		return lpadChar;
	}

	public void setLpadChar(char lpadChar) {
		this.lpadChar = lpadChar;
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
				+ ", lpadLength=" + lpadLength + ", lpadChar=" + lpadChar
				+ ", increment=" + increment + "]";
	}
	
	public String getSequenceStr(boolean dateStr){
		if(dateStr){
			return getSequenceStr(DateUtil.getCurrDateStr());
		}else{
			return getSequenceStr(null);
		}
	}
	
	public String getSequenceStr(String middleStr){
		
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotEmpty(this.getPrefix())){
			sb.append(this.getPrefix());
		}
		
		sb.append(middleStr);
		
		sb.append(StringUtils.leftPad(this.getCurrValue().toString(), this.getLpadLength(), this.getLpadChar()));
		
		if(StringUtils.isNotEmpty(this.getSuffix())){
			sb.append(this.getSuffix());
		}
		return sb.toString();
	}

}
