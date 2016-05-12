package com.standard.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.standard.api.model.IwritePolhdr;
import com.standard.api.model.IwritePolhdrId;

import sici.tools.message.Message;
import sici.tools.message.MessageType;

public class PolicyTest extends AbstractTest
{
//	@Test
//	public void unifiedTest() throws Exception
//	{
//		testSave();
//		testGet();
//	}
	
	@Test
	public void testSave() throws Exception
	{
		ObjectMapper om = new ObjectMapper();
		String body = om.writeValueAsString(createValidPolicy());
		mock().perform(post("/policy/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testSave_incorrectPolstatus() throws Exception
	{
		ObjectMapper om = new ObjectMapper();
		
		IwritePolhdr policy = createValidPolicy();
		policy.setPolstatus("ABC");
		String body = om.writeValueAsString(policy);
		
		MvcResult result = mock().perform(post("/policy/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isBadRequest())
				.andReturn();
		
		Message message = om.readValue(result.getResponse().getContentAsString(), Message.class);
		System.out.println("Error message: " + message.getMessage());
		System.out.println("Message Type: " + message.getType().toString());
		if(message.getType() == MessageType.ERROR)
			System.out.println("Error? " + "Yes");
		else
			System.out.println("Error? " + "No");
	}
	
	@Test
	@Transactional
	public void testGet() throws Exception
	{
		testSave(); // reuse for test
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("branchCode", "IT");
		params.add("prodcode", "PCP");
		params.add("polno", "1234");
		mock().perform(get("/policy/get").params(params)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.assuredName1").value("JON KEVIN D HUBILLA"));
	}
	
	private IwritePolhdr createValidPolicy()
	{
		IwritePolhdr policy = new IwritePolhdr();
		IwritePolhdrId id = new IwritePolhdrId("IT", "PCP", 1234);
		policy.setId(id);
		
		policy.setAssuredName1("JON KEVIN D HUBILLA");
		
		
		return policy;
	}
	
	
	private String createValidPolicyJson()
	{
		return "{\r\n" + 
				"  \"id\": {\r\n" + 
				"    \"branchCode\": \"IT\",\r\n" + 
				"    \"prodcode\": \"PCP\",\r\n" + 
				"    \"polno\": 1234\r\n" + //dummy ID
				"  },\r\n" + 
				"  \"prevpolno\": null,\r\n" + 
				"  \"extpolno\": null,\r\n" + 
				"  \"accountType\": \"S\",\r\n" + 
				"  \"paymentPlan\": \"O\",\r\n" + 
				"  \"polstatus\": \"ABC\",\r\n" + 
				"  \"inceptionDate\": \"2016-04-18\",\r\n" + 
				"  \"expiryDate\": \"2017-04-18\",\r\n" + 
				"  \"issueDate\": \"2016-03-30\",\r\n" + 
				"  \"origInception\": \"2016-03-30\",\r\n" + 
				"  \"daysInsured\": 365,\r\n" + 
				"  \"clientNo\": 30000599,\r\n" + 
				"  \"masterClient\": null,\r\n" + 
				"  \"assuredName1\": \"JON KEVIN D HUBILLA\",\r\n" + 
				"  \"assuredName2\": null,\r\n" + 
				"  \"renMethod\": \"02\",\r\n" + 
				"  \"agentType\": \"AGT\",\r\n" + 
				"  \"agentBranch\": \"IT\",\r\n" + 
				"  \"agentCode\": 20000194,\r\n" + 
				"  \"marketingGroup\": \"RMG\",\r\n" + 
				"  \"subagentCode\": null,\r\n" + 
				"  \"latestTrnType\": \"NB\",\r\n" + 
				"  \"lob\": \"Motor\",\r\n" + 
				"  \"uwLock\": null,\r\n" + 
				"  \"uploadStatus\": null,\r\n" + 
				"  \"dateCreated\": 1460970007254,\r\n" + 
				"  \"createdBy\": \"seamsadm\",\r\n" + 
				"  \"dateUpdated\": 1460970007254,\r\n" + 
				"  \"updatedBy\": \"seamsadm\",\r\n" + 
				"  \"referralNumber\": null,\r\n" + 
				"  \"approvalNumber\": null,\r\n" + 
				"  \"slipNumber\": null,\r\n" + 
				"  \"originalCurrency\": \"PHP\",\r\n" + 
				"  \"accountingCurrency\": \"PHP\",\r\n" + 
				"  \"timeOption\": \"12N\",\r\n" + 
				"  \"cancellationDate\": null,\r\n" + 
				"  \"cancellationReason\": null,\r\n" + 
				"  \"underwritingFlag\": \"N\",\r\n" + 
				"  \"claimsFlag\": \"N\",\r\n" + 
				"  \"acountingFlag\": \"N\",\r\n" + 
				"  \"exhangeRate\": 1,\r\n" + 
				"  \"batchNumber\": null,\r\n" + 
				"  \"effectiveDate\": \"2016-04-18\",\r\n" + 
				"  \"entype\": null,\r\n" + 
				"  \"latestUwYear\": 2016,\r\n" + 
				"  \"lastNbrnPostingDate\": null,\r\n" + 
				"  \"totalSumInsured\": 0,\r\n" + 
				"  \"poljacketNumber\": null,\r\n" + 
				"  \"currentInception\": \"2016-03-30\",\r\n" + 
				"  \"currentExpiry\": \"2017-03-30\",\r\n" + 
				"  \"prBatchNumber\": null,\r\n" + 
				"  \"postingDatePriorPr\": null,\r\n" + 
				"  \"iosClientNumber\": null,\r\n" + 
				"  \"cocFlag\": null,\r\n" + 
				"  \"correctProdcode\": \"PCP\",\r\n" + 
				"  \"riAcctType\": \"L\",\r\n" + 
				"  \"lob2\": \"Motor\"\r\n" + 
				"}";
	}
}
