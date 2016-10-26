package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IACLDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.auth.ACL;

@Repository
public class ACLDAO extends BaseDAO<ACL> implements IACLDAO{

	private static final Po<ACL> acl = new Po<ACL>(ACL.class);

	
}
