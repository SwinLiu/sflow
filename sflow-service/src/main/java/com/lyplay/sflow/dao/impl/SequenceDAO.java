package com.lyplay.sflow.dao.impl;

import com.lyplay.sflow.dao.ISequenceDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.po.Sequence;

public class SequenceDAO extends BaseDAO implements ISequenceDAO{

	@Override
	public Sequence getNextSequence(String sequenceName) {
		return null;
	}

	
	
}
