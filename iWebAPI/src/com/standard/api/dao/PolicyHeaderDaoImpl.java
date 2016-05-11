package com.standard.api.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.standard.api.model.IwritePolhdr;
import com.standard.api.model.IwritePolhdrId;

public class PolicyHeaderDaoImpl implements PolicyHeaderDao 
{
	private SessionFactory sessionFactory;

	@Override
	public IwritePolhdr get(IwritePolhdrId id) 
	{
		Session session = sessionFactory.openSession();
		
		IwritePolhdr policy = session.get(IwritePolhdr.class, id);
		
		session.flush();
		session.close();
		
		return policy;
	}

	@Override
	public void save(IwritePolhdr polhdr)
	{
		Session session = sessionFactory.openSession();
		
		session.save(polhdr);
		
		session.flush();
		session.close();

	}

	@Override
	public void update(IwritePolhdr polhdr) 
	{
		Session session = sessionFactory.openSession();
		
		session.update(polhdr);
		
		session.flush();
		session.close();


	}

	@Override
	public void delete(IwritePolhdr polhdr)
	{
		Session session = sessionFactory.openSession();
		
		session.delete(polhdr);
		
		session.flush();
		session.close();


	}

	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}

}
