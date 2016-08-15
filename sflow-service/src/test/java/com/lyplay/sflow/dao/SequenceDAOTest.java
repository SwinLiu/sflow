package com.lyplay.sflow.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.po.Sequence;

public class SequenceDAOTest extends BaseTest{

	private String sequenceName = "CodeDemo";
	
	@Resource
	private ISequenceDAO sequenceDAO;
	
	@Test
	public void saveTest(){
		
		Sequence sequence = new Sequence(sequenceName, "P", 1L, "S", 10, 1);
		sequenceDAO.saveSequence(sequence);
		
	}
	
	public void getTest(){
		
		Sequence sequence = sequenceDAO.getNextSequence(sequenceName);
		
	}
	
}
