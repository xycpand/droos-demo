package com.defang;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @author mali
 */
@ComponentScan({ "com.defang" })
public class DroolsApp {
	public static void main(String[] args) {
		new SpringApplication(DroolsApp.class).run(args);
	}

}
