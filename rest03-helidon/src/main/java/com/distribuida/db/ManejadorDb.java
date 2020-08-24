package com.distribuida.db;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;


//import org.apache.commons.dbcp2.BasicDataSource;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;

@ApplicationScoped
public class ManejadorDb {
	
	@Produces @ApplicationScoped
	public DbClient db() {
		Config config = Config.create();
		
		Config dbconfig = config.get("db");
		DbClient dbclient = DbClient.builder(dbconfig)
				.build();
		return dbclient;
	}
	

}
