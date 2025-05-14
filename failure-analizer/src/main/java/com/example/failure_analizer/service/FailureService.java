package com.example.failure_analizer.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.example.failure_analizer.entity.Failure;
import com.example.failure_analizer.exceptions.NoRecordFoundException;
import com.example.failure_analizer.repository.FailureRepository;





import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Service
public class FailureService {
	
	@Autowired
	private FailureRepository failureRepository;
	
	//create failure record 
	public Failure createFailureRecord(Failure failure) {
		Failure saveFailureRecord = failureRepository.save(failure);
		return saveFailureRecord;
		}
	
	//get all records
	public List<Failure> getAllFailureRecords() {
		List<Failure> getAllRecords = failureRepository.findAll();
		if(getAllRecords.isEmpty()) {
			throw new NoRecordFoundException("No Records Found");
		}
		return getAllRecords;
	}
	
	 //get record by userId
	public Failure getRecordById(Long id) {
		
		Optional<Failure> opt =  failureRepository.findById(id);
		   if(opt.isPresent()) {
			return opt.get();
		   }
		  else {
			throw new NoRecordFoundException("No Records Found");
		}
	}
	
	
	   
	 
	

}
