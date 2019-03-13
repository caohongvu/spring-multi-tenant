package com.example;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultitenantConfiguration {

	@Autowired
	private DataSourceProperties properties;

	/**
	 * Defines the data source for the application
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		Map<Object, Object> resolvedDataSources = new HashMap<>();
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		try {

			String tenantId = "default";

			dataSourceBuilder.driverClassName(properties.getDriverClassName()).url(properties.getUrl())
					.username(properties.getUsername()).password(properties.getPassword());

			if (properties.getType() != null) {
				dataSourceBuilder.type(properties.getType());
			}

			resolvedDataSources.put(tenantId, dataSourceBuilder.build());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		MultitenantDataSource dataSource = MultitenantDataSource.getInstance();
		dataSource.setDefaultTargetDataSource(defaultDataSource());
		dataSource.setTargetDataSources(resolvedDataSources);

		// Call this to finalize the initialization of the data source.
		dataSource.afterPropertiesSet();

		return dataSource;
	}

	/**
	 * Creates the default data source for the application
	 * 
	 * @return
	 */
	private DataSource defaultDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(properties.getDriverClassName()).url(properties.getUrl())
				.username(properties.getUsername()).password(properties.getPassword());

		if (properties.getType() != null) {
			dataSourceBuilder.type(properties.getType());
		}

		return dataSourceBuilder.build();
	}
}
