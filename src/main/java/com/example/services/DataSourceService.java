package com.example.services;

import java.util.List;

import com.example.domain.DataSource;

public interface DataSourceService {
	List<DataSource> getAll();
	DataSource save(DataSource datasource);
}
