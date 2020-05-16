package com.olmero.tender.controller;

import com.olmero.tender.entity.Bidder;
import com.olmero.tender.service.BidderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bidder")
public class BidderController {

	@Autowired
	private BidderService bidderService;

	@PostMapping
	public ResponseEntity create(@RequestParam String name) {
		Bidder bidder = new Bidder();
		bidder.setBidderName(name);
		bidderService.save(bidder);
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
