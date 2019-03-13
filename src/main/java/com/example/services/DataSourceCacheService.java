package com.example.services;

import java.util.HashMap;

import com.example.domain.DataSource;

public class DataSourceCacheService extends HashMap<String, DataSource>{
	private static final long serialVersionUID = 1L;
	private static DataSourceCacheService dataSourceCacheService = null;
	
	private DataSourceCacheService() {};
	
	public static DataSourceCacheService getInstance() {
		if(dataSourceCacheService == null) {
			dataSourceCacheService = new DataSourceCacheService();
		}
		return dataSourceCacheService;
	}
    
}
