package com.olmero.tender.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Offer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "isAccepted")
	private Boolean isAccepted;

	@ManyToOne
	@JoinColumn(name = "tender_id")
	private Tender tender;

	@ManyToOne
	@JoinColumn(name = "bidder_id")
	private Bidder bidder;
}
