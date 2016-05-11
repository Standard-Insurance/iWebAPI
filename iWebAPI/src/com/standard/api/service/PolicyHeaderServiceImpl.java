package com.standard.api.service;

import com.standard.api.dao.PolicyHeaderDao;
import com.standard.api.model.IwritePolhdr;
import com.standard.api.model.IwritePolhdrId;

public class PolicyHeaderServiceImpl implements PolicyHeaderService
{

	private PolicyHeaderDao policyHeaderDao;
	
	@Override
	public IwritePolhdr get(IwritePolhdrId id)
	{
		return policyHeaderDao.get(id);
	}

	@Override
	public void save(IwritePolhdr polhdr) 
	{
		policyHeaderDao.save(polhdr);
		
	}

	@Override
	public void update(IwritePolhdr polhdr) 
	{
		policyHeaderDao.update(polhdr);
		
	}

	@Override
	public void delete(IwritePolhdrId id) 
	{
		IwritePolhdr polhdr = get(id);
		if(polhdr != null)
			policyHeaderDao.delete(polhdr);
		
	}
	
	public void setPolicyHeaderDao(PolicyHeaderDao policyHeaderDao) 
	{
		this.policyHeaderDao = policyHeaderDao;
	}

}
