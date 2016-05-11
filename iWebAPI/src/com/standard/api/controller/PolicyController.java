package com.standard.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.standard.api.model.IwritePolhdr;
import com.standard.api.model.IwritePolhdrId;
import com.standard.api.service.PolicyHeaderService;

@RestController
public class PolicyController 
{
	@Autowired PolicyHeaderService policyHeaderService;
	
	@RequestMapping(value = "/policy/get", method = RequestMethod.GET)
	public ResponseEntity<IwritePolhdr> get(@RequestParam("branchCode") String branchCode, @RequestParam("prodcode") String prodcode, @RequestParam("polno") int polno)
	{
		IwritePolhdr policy = policyHeaderService.get(new IwritePolhdrId(branchCode, prodcode, polno));
		
		if(policy == null)
			return new ResponseEntity<IwritePolhdr>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<IwritePolhdr>(policy, HttpStatus.OK);
				
				
		//return policyHeaderService.get(new IwritePolhdrId(branchCode, prodcode, polno));
	}
	
	@RequestMapping(value = "/policy/post", method = RequestMethod.POST)
	public ResponseEntity<Object> post(@RequestBody IwritePolhdr policy)
	{
		try
		{
			policyHeaderService.save(policy);
		}
		catch(Exception e)
		{
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/policy/update", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody IwritePolhdr policy)
	{
		try
		{
			
			policyHeaderService.update(policy);
		}
		catch(Exception e)
		{
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/policy/delete/{branchCode}/{prodcode}/{polno}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable String branchCode, @PathVariable String prodcode, @PathVariable int polno)
	{
		try
		{
			
			policyHeaderService.delete(new IwritePolhdrId(branchCode, prodcode, polno));
		}
		catch(Exception e)
		{
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
	
}
