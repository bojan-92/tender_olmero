package com.olmero.tender.repository;

import com.olmero.tender.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {

	List<Tender> findAllByIssuerId(Long issuerId);
}
