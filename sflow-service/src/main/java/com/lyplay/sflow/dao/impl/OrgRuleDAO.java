package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IOrgRuleDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.OrgRule;

@Repository
public class OrgRuleDAO extends BaseDAO<OrgRule> implements IOrgRuleDAO{

	private static final Po<OrgRule> orgRule = new Po<OrgRule>(OrgRule.class);
	
	
}
