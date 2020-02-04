package com.fci.engaly.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fci.engaly.model.Employee;

/**
 * Add the following code to the Dao interface that extends the mongo Repository
 * to automatically handle the crud queries. this class have all mongo database
 * operations you extended MongoRepository that have mongo database operations
 * you can get object from that interface in any place that you can use mongo
 * operations MongoRepository it take two parameters model(collection) that
 * will aplay all operation about it.
 */
@Repository
public interface Employeedao extends MongoRepository<Employee, String> {

}
