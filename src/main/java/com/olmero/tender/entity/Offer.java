package com.olmero.tender.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@JsonIgnore
	private Tender tender;

	@ManyToOne
	@JoinColumn(name = "bidder_id")
	@JsonIgnore
	private Bidder bidder;
}
