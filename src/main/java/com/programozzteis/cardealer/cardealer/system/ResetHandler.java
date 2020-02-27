package com.programozzteis.cardealer.cardealer.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@ConfigurationProperties
@Controller
public class ResetHandler {

	@Value("${spring.datasource.driver-class-name}")
	private String dbDriverName;
	@Value("${spring.datasource.url}")
	private String dbURL;
	@Value("${spring.datasource.username}")
	private String dbPWD;
	@Value("${spring.datasource.password}")
	private String dbUser;
	@Value("${database}")
	private String dbType;
	
	
	@GetMapping("/reset")
	public String makeDBReset() {
		/** Build-up the DB Connection */
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(dbDriverName);
	    dataSource.setUrl(dbURL);
	    dataSource.setUsername(dbPWD);
	    dataSource.setPassword(dbUser);

	    /** Do Schema and Data ReInitialization by SQL Scripts */
	    Resource initSchema = new ClassPathResource("db/"+dbType+"/schema.sql");
	    Resource initData = new ClassPathResource("db/data.sql");
	    DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
	    DatabasePopulatorUtils.execute(databasePopulator, dataSource);

	    /** Go back to HomePage */
	    return "redirect:/";
	}
	
}
