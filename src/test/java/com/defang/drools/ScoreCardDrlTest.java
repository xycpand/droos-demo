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

import com.defang.scorecard_drl.entity.Applicant;



@RunWith(SpringRunner.class)
public class ScoreCardDrlTest {
   
    private static final String SERVER_URL = "http://192.168.80.236:8180/kie-server/services/rest/server";
	private static final String USERNAME = "kieserver";
	private static final String PASSWORD = "kieserver1!";
	private static final String KIE_CONTAINER_ID = "scorecard_drl_1.0.0-SNAPSHOT";
	private static final String KIE_SESSION_ID = "scorecard_drl";
    
	  @Test
	  public void testScoreCardDrl() {
		// KisService 配置信息设置
	        KieServicesConfiguration kieServicesConfiguration =
	                KieServicesFactory.newRestConfiguration(SERVER_URL, USERNAME, PASSWORD, 10000L);
	        kieServicesConfiguration.setMarshallingFormat(MarshallingFormat.JSON);

	        // 创建规则服务客户端
	        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(kieServicesConfiguration);
	        RuleServicesClient ruleServicesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);

	        // 规则输入条件  Applicant( age,sex,maritalStatus,degree,score)
	        //Applicant applicant = new Applicant(23,"1","1","1",0);
	        Applicant applicant = new Applicant(23,"0","0","0",0);

	        // 命令定义，包含插入数据，执行规则
	        KieCommands kieCommands = KieServices.Factory.get().getCommands();
	        List<Command<?>> commands = new LinkedList<>();
	        commands.add(kieCommands.newInsert(applicant, "applicant"));
	        //commands.add(kieCommands.newGetFactHandle(new RuleNameStartsWithAgendaFilter("scorecard")));
	        //commands.add(kieCommands.newFireAllRules());
	        commands.add(kieCommands.newFireAllRules("applicantTest"));
	        ServiceResponse<ExecutionResults> results = ruleServicesClient.executeCommandsWithResults(KIE_CONTAINER_ID,
	                kieCommands.newBatchExecution(commands, KIE_SESSION_ID));

	         // 返回值读取
	        Applicant newApplicant = (Applicant) results.getResult().getValue("applicant");
		    System.out.println(newApplicant.toString());
		    
		    ExecutionResults result = results.getResult(); //获取请求
		    int fired = (Integer)result.getValue("applicantTest");
		    System.out.println("执行规则的条数是："+fired);
	  }
	

}
