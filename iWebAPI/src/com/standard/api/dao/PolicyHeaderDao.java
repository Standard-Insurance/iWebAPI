package com.standard.api.dao;

import com.standard.api.model.IwritePolhdr;
import com.standard.api.model.IwritePolhdrId;

public interface PolicyHeaderDao
{
	public IwritePolhdr get(IwritePolhdrId id);
	public void save(IwritePolhdr polhdr);
	public void update(IwritePolhdr polhdr);
	public void delete(IwritePolhdr polhdr);
}
