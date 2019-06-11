package com.defang.scorecard.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.defang.common.ResultEntity;
import com.defang.framework.sso.util.SsoUtils;
import com.defang.scorecard.entity.Applicant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/drools")
@Controller
@Api("规则引擎接口")
public class DroolsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DroolsController.class);

	@Autowired
	SsoUtils ssoUtils;

    private static final String SERVER_URL = "http://192.168.80.236:8180/kie-server/services/rest/server";
	private static final String USERNAME = "kieserver";
	private static final String PASSWORD = "kieserver1!";

	@ApiOperation(value = "/testScoreCard", notes = "积分卡",httpMethod ="POST")
	@RequestMapping(value = "/testScoreCard", method = { RequestMethod.POST })
	@ResponseBody
	public Object getDataKey(@RequestBody @Valid Applicant applicant,
			String kieSessionId,String kieContainerId) {
//		User user = SsoUtils.getUser();
//		if (null==user) {
//			String systemToken = ssoUtils.getSystemToken();
//			Assert.notNull(systemToken, "用户未登录");
//		}
	    LOGGER.info("执行规则前："+ applicant.toString());
		ResultEntity<Object> resultEntity = null;
		try {
			// KisService 配置信息设置
	        KieServicesConfiguration kieServicesConfiguration =
	                KieServicesFactory.newRestConfiguration(SERVER_URL, USERNAME, PASSWORD, 10000L);
	        kieServicesConfiguration.setMarshallingFormat(MarshallingFormat.JSON);

	        // 创建规则服务客户端
	        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(kieServicesConfiguration);
	        RuleServicesClient ruleServicesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);


	        // 命令定义，包含插入数据，执行规则
	        KieCommands kieCommands = KieServices.Factory.get().getCommands();
	        List<Command<?>> commands = new LinkedList<>();
	        commands.add(kieCommands.newInsert(applicant, "applicant"));
	        commands.add(kieCommands.newFireAllRules());
	        ServiceResponse<ExecutionResults> results = ruleServicesClient.executeCommandsWithResults(kieContainerId,
	                kieCommands.newBatchExecution(commands, kieSessionId));

	         // 返回值读取
	        Applicant newApplicant = (Applicant) results.getResult().getValue("applicant");
		    LOGGER.info("执行规则后："+newApplicant.toString());
		    
		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("applicant", newApplicant);
			resultEntity = ResultEntity.success(map);
		} catch (Exception e) {
			LOGGER.error("系统错误", e);
			resultEntity = ResultEntity.error("系统错误");
		}
		return resultEntity;
	}



}