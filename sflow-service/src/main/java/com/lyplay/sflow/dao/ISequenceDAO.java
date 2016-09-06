package com.lyplay.sflow.dao;

import com.lyplay.sflow.orm.IBaseDAO;
import com.lyplay.sflow.po.Sequence;


public interface ISequenceDAO extends IBaseDAO<Sequence>{

	public Sequence getNextSequence(String sequenceName);
	
	public void saveSequence(Sequence sequence);
	
}
