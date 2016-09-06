package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IOrgTypeRuleDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.OrgTypeRule;

@Repository
public class OrgTypeRuleDAO extends BaseDAO<OrgTypeRule> implements IOrgTypeRuleDAO{

	private static final Po<OrgTypeRule> orgTypeRule = new Po<OrgTypeRule>(OrgTypeRule.class);
	
	
}
