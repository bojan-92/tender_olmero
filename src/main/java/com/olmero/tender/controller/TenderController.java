package com.olmero.tender.controller;

import com.olmero.tender.entity.Issuer;
import com.olmero.tender.entity.Tender;
import com.olmero.tender.exception.IssuerNotFoundException;
import com.olmero.tender.exception.TenderNotFoundException;
import com.olmero.tender.service.IssuerService;
import com.olmero.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("tender")
public class TenderController {

	@Autowired
	private TenderService tenderService;

	@Autowired
	private IssuerService issuerService;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Tender getTender(@PathVariable Long id){
		Tender tender = tenderService.findById(id);
		if(tender == null){
			throw new TenderNotFoundException("Tender with id - " + id);
		}
		return tender;
	}

	@PostMapping
	public ResponseEntity createTender(@RequestBody CreateTenderRequest tenderRequest){
		Issuer issuer = issuerService.findById(tenderRequest.getIssuerId());
		if(issuer == null){
			throw new IssuerNotFoundException("Issuer with id - " + tenderRequest.getIssuerId());
		}
		tenderRequest.getTender().setIssuer(issuer);
		tenderService.save(tenderRequest.getTender());

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(tenderRequest.getTender().getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
