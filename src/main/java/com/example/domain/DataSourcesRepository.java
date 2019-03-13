package com.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourcesRepository extends JpaRepository<DataSource, Integer> {

}