package com.example;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultitenantDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getInstance().getCurrentTenant();
    }
    private MultitenantDataSource() {}
    
    private static MultitenantDataSource multitenantDataSource = null;
    public static MultitenantDataSource getInstance() {
    	if(multitenantDataSource == null) {
    		multitenantDataSource = new MultitenantDataSource();
    	}
    	return multitenantDataSource;
    }
    
}
