package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.TenantContext;
import com.example.domain.DataSource;
import com.example.services.DataSourceService;

@Controller
public class DataSourceController {
    @Autowired
    private DataSourceService dataSourceService;

    @RequestMapping(path = "/datasource", method= RequestMethod.POST)
    public ResponseEntity<?> createDataSource(@RequestBody DataSource datasource) {
        TenantContext.getInstance().setCurrentTenant(null);
        dataSourceService.save(datasource);
        return ResponseEntity.ok(datasource);
    }
    
    @RequestMapping(path = "/datasource", method= RequestMethod.GET)
    public ResponseEntity<?> createDataSource() {
        return ResponseEntity.ok(dataSourceService.getAll());
    }
}
