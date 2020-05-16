package com.olmero.tender.controller;

import com.olmero.tender.entity.Issuer;
import com.olmero.tender.service.IssuerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("issuer")
public class IssuerController {

	@Autowired
	private IssuerService issuerService;

	@PostMapping
	public ResponseEntity create(@RequestParam String name){
		Issuer issuer = new Issuer();
		issuer.setIssuerName(name);
		issuerService.save(issuer);
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
