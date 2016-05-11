package com.standard.api.service;

import com.standard.api.model.IwritePolhdr;
import com.standard.api.model.IwritePolhdrId;

public interface PolicyHeaderService 
{
	public IwritePolhdr get(IwritePolhdrId id);
	public void save(IwritePolhdr polhdr);
	public void update(IwritePolhdr polhdr);
	public void delete(IwritePolhdrId id);
}
