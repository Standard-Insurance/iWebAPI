package com.standard.api.dao;

import com.standard.api.model.IwritePolhdr;
import com.standard.api.model.IwritePolhdrId;

import sici.tools.hibernate.AbstractDao;

public class PolicyHeaderDaoImpl extends AbstractDao<IwritePolhdr, IwritePolhdrId> implements PolicyHeaderDao 
{
	@Override
	public Class<IwritePolhdr> getClazz()
	{
		return IwritePolhdr.class;
	}

}
