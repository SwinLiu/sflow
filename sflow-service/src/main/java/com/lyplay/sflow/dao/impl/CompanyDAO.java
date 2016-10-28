package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.ICompanyDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.Company;

@Repository
public class CompanyDAO extends BaseDAO<Company> implements ICompanyDAO{

	private static final Po<Company> company = new Po<Company>(Company.class);

	
}
