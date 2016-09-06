package com.lyplay.sflow.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.ISequenceDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.po.Sequence;

@Repository
public class SequenceDAO extends BaseDAO<Sequence> implements ISequenceDAO{

	private static final String TABLE_NAME = "sflow_sequence";
	
	@Override
	public Sequence getNextSequence(final String sequenceName) {
		return this.getJdbcTemplate().execute(new CallableStatementCreator(){

			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				
				String storedProc = "{call nextval (?,?,?,?,?)}";  
	            CallableStatement cs = con.prepareCall(storedProc);   
	            // 设置输入参数的值   
	            cs.setString(1, sequenceName);
	            // 注册输出参数的类型   
	            cs.registerOutParameter(2,Types.VARCHAR);	//out_prefix
	            cs.registerOutParameter(3,Types.BIGINT);	//out_curr_value
	            cs.registerOutParameter(4,Types.VARCHAR);	//out_suffix
	            cs.registerOutParameter(5,Types.INTEGER);	//out_lpad_length
	            return cs;
			}
			
		}, new CallableStatementCallback<Sequence>(){

			@Override
			public Sequence doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				// 获取输出参数的值
				Sequence sequence = new Sequence();
				sequence.setSequenceName(sequenceName);
				sequence.setPrefix(cs.getString(2));
				sequence.setCurrValue(cs.getLong(3));
				sequence.setSuffix(cs.getString(4));
				sequence.setLpadLength(cs.getInt(5));
				return sequence;
			}
			
		});
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
