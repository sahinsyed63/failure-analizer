package com.example.failure_analizer.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.failure_analizer.entity.Failure;
import com.example.failure_analizer.service.FailureService;
import com.example.failure_analizer.utils.ReportGenerator;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/failures")
public class FailureController {
	
	private static final Logger logger = LoggerFactory.getLogger(FailureController.class);
	
     @Autowired
	private FailureService failureService;
     
     private ReportGenerator reportGenerator;
     public FailureController(ReportGenerator reportGenerator) {
    	 this.reportGenerator =reportGenerator;
     }
     
     @PostMapping("/create/record")
     public ResponseEntity<Failure> createFailureRecord(@Valid @RequestBody Failure failure){ 
         Failure createFailure =   failureService.createFailureRecord(failure);
    	 logger.info("Create Failure Record: ");
    	 return new ResponseEntity<> (createFailure,HttpStatus.CREATED);
    	 
     }
     
     @GetMapping("/get/all")
     public ResponseEntity<List<Failure>> getAllFailureRecords() {
    	           logger.info("get All records ");
    	          List<Failure> failures = failureService.getAllFailureRecords();
    	           return new ResponseEntity<>(failures,HttpStatus.OK);
     }
     
     @GetMapping(value ="/report",produces=MediaType.APPLICATION_PDF_VALUE)
     public ResponseEntity generateReport() {
    	 
    	 List<Failure> failures = failureService.getAllFailureRecords();
    	ByteArrayInputStream bis = reportGenerator.generatePdf(failures);
    	 HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Disposition", "inline; filename=failure-report.pdf");

    	 return ResponseEntity.ok()
    			 .headers(headers)
    			 .contentType(MediaType.APPLICATION_PDF)
    			// .body(new InputStreamResource(reportGenerator.generatePdf(failures)))
    			 .body(new InputStreamResource(bis));
    			 
     }
     
     @GetMapping("/greeting")
     public ResponseEntity<String> getMsg() {
    	 return ResponseEntity.ok("Hello My First api");
     }
     
     @GetMapping("/{id}")
     public ResponseEntity<Failure> getRecordById(@PathVariable Long id) {
    	 Failure failure = failureService.getRecordById(id);
    	 return new ResponseEntity<>(failure,HttpStatus.OK);
     }
     
}
