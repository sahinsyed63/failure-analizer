package com.example.failure_analizer.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
@Entity
public class Failure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "title shound not be empty")
	private String title;
	@NotNull
	private String description;
	private String rootCause;
	private String findings;
	private String lessonLearned;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateReported;
	
	public Failure() {
		
	}
	
	public Failure(String title, String description, String rootCause, String findings, String lessonLearned ,LocalDate dateReported) {
		this.title = title;
		this.description = description;
		this.rootCause = rootCause;
		this.findings = findings;
		this.lessonLearned = lessonLearned;
		this.dateReported = dateReported;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getFindings() {
		return findings;
	}

	public void setFindings(String findings) {
		this.findings = findings;
	}

	public String getLessonLearned() {
		return lessonLearned;
	}

	public void setLessonLearned(String lessonLearned) {
		this.lessonLearned = lessonLearned;
	}

	public LocalDate getDateReported() {
		return dateReported;
	}

	public void setDateReported(LocalDate dateReported) {
		this.dateReported = dateReported;
	}
	
	

}
