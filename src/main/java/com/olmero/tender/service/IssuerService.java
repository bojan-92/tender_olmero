package com.olmero.tender.service;

import com.olmero.tender.entity.Issuer;
import com.olmero.tender.entity.Offer;
import com.olmero.tender.repository.IssuerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IssuerService {

	@Autowired
	private IssuerRepository issuerRepository;

	public Issuer findById(Long id){
		Optional<Issuer> issuer = issuerRepository.findById(id);
		return issuer.orElse(null);
	}

	public void save(Issuer issuer) {
		issuerRepository.save(issuer);
	}
}
