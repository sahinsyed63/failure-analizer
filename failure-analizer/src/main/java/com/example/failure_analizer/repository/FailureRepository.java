package com.example.failure_analizer.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.failure_analizer.entity.Failure;

public interface FailureRepository extends JpaRepository<Failure,Long> {
    List<Failure> findByDateReportedBetween(LocalDate fromDate, LocalDate toDate);


}
