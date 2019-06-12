package com.defang.drools;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.springframework.test.context.junit4.SpringRunner;

import com.defang.decisiontable_spreadsheet.Person;



@RunWith(SpringRunner.class)
public class DecisiontableSpreadsheetTest {
   
    private static final String SERVER_URL = "http://192.168.80.236:8180/kie-server/services/rest/server";
	private static final String USERNAME = "kieserver";
	private static final String PASSWORD = "kieserver1!";
	private static final String KIE_CONTAINER_ID = "decisiontable_spreadsheet_1.0.0-SNAPSHOT";
	private static final String KIE_SESSION_ID = "decisiontable_spreadsheet";
    
	  @Test
	  public void testDecisiontableSpreadsheet() {
		// KisService 配置信息设置
	        KieServicesConfiguration kieServicesConfiguration =
	                KieServicesFactory.newRestConfiguration(SERVER_URL, USERNAME, PASSWORD, 10000L);
	        kieServicesConfiguration.setMarshallingFormat(MarshallingFormat.JSON);

	        // 创建规则服务客户端
	        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(kieServicesConfiguration);
	        RuleServicesClient ruleServicesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);

	        // 规则输入条件  Applicant( age,sex,maritalStatus,degree,score)
	        Person p1 = new Person("奥巴马", 68);
	        Person p2 = new Person("普京", 32);
	        Person p3 = new Person("朴槿惠", 18);
	        Person p4 = new Person("川普", 10);
	        Person p5 = new Person("金正恩", 66);
	 
	        System.out.println("before p1 : " + p1);
	        System.out.println("before p2 : " + p2);
	        System.out.println("before p3 : " + p3);
	        System.out.println("before p4 : " + p4);
	        System.out.println("before p5 : " + p5);


	        // 命令定义，包含插入数据，执行规则
	        KieCommands kieCommands = KieServices.Factory.get().getCommands();
	        List<Command<?>> commands = new LinkedList<>();
	        commands.add(kieCommands.newInsert(p1, "p1"));
	        commands.add(kieCommands.newInsert(p2, "p2"));
	        commands.add(kieCommands.newInsert(p3, "p3"));
	        commands.add(kieCommands.newInsert(p4, "p4"));
	        commands.add(kieCommands.newInsert(p5, "p5"));
	        commands.add(kieCommands.newFireAllRules());
	        ServiceResponse<ExecutionResults> results = ruleServicesClient.executeCommandsWithResults(KIE_CONTAINER_ID,
	                kieCommands.newBatchExecution(commands, KIE_SESSION_ID));

		    System.out.println(results.toString());
	         // 返回值读取
	        Person newP1= (Person) results.getResult().getValue("p1");
		    System.out.println(newP1.toString());
		    
	        Person newP2= (Person) results.getResult().getValue("p2");
		    System.out.println(newP2.toString());
		    
	        Person newP3= (Person) results.getResult().getValue("p3");
		    System.out.println(newP3.toString());
		    
	        Person newP4= (Person) results.getResult().getValue("p4");
		    System.out.println(newP4.toString());
		    
	        Person newP5= (Person) results.getResult().getValue("p5");
		    System.out.println(newP5.toString());
	  }
	

}
