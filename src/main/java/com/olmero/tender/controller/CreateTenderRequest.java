package com.olmero.tender.controller;

import com.olmero.tender.entity.Tender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTenderRequest {

	private Tender tender;
	private Long issuerId;
}
