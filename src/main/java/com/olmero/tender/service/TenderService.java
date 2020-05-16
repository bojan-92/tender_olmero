package com.olmero.tender.service;

import com.olmero.tender.entity.Tender;
import com.olmero.tender.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenderService {

	@Autowired
	private TenderRepository tenderRepository;

	public void save(Tender tender) {
		tenderRepository.save(tender);
	}

	public Tender findById(Long id){
		Optional<Tender> tender = tenderRepository.findById(id);
		return tender.orElse(null);
	}

	public List<Tender> findAllByIssuerId(Long issuerId){
		return tenderRepository.findAllByIssuerId(issuerId);
	}

}
