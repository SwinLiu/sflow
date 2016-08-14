package com.lyplay.sflow.dao;

import com.lyplay.sflow.po.Sequence;


public interface ISequenceDAO{

	public Sequence getNextSequence(String sequenceName);
	
}
