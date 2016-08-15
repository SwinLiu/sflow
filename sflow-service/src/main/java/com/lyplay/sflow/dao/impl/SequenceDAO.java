package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.ISequenceDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.po.Sequence;

@Repository
public class SequenceDAO extends BaseDAO implements ISequenceDAO{

	@Override
	public Sequence getNextSequence(String sequenceName) {
		return null;
	}
	
	@Override
	public void saveSequence(Sequence sequence) {
		try {
			this.save(sequence);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
