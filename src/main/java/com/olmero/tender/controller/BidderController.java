package com.olmero.tender.controller;

import com.olmero.tender.entity.Bidder;
import com.olmero.tender.service.BidderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bidder")
public class BidderController {

	@Autowired
	private BidderService bidderService;

	@PostMapping
	public ResponseEntity create(@RequestBody CreateBidderRequest request) {
		Bidder bidder = new Bidder();
		bidder.setBidderName(request.getBidderName());
		bidderService.save(bidder);
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
