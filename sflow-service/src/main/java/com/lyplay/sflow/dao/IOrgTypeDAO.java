package com.lyplay.sflow.dao;

import java.util.List;

import com.lyplay.sflow.orm.IBaseDAO;
import com.lyplay.sflow.po.OrgType;

/**
 * 
 * @author lyplay
 *
 */

public interface IOrgTypeDAO extends IBaseDAO<OrgType> {

	/**
	 * get all the org type
	 * @return
	 */
	public List<OrgType> list();

	/**
	 * add org type rule
	 * @param pid
	 * @param cid
	 * @param num
	 * @return
	 */
	public boolean addOrgTypeRule(int pid, int cid, int num);

	/**
	 * delete org type rule
	 * @param pid
	 * @param cid
	 * @return
	 */
	public boolean delOrgTypeRule(int pid, int cid);
	
	/**
	 * update org type rule, update num
	 * @param pid
	 * @param cid
	 * @param num
	 * @return
	 */
	public boolean updateOrgTypeRule(int pid, int cid, int num);
	
	/**
	 * get all the org from parent org id
	 * @param pid
	 * @return
	 */
	public List<OrgType> listByRule(int pid);
	
	/**
	 * get the num 
	 * @param pid
	 * @param cid
	 * @return
	 */
	public int getNum(int pid, int cid);

}
