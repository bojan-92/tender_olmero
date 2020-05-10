package com.olmero.tender.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "issuers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Issuer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "issuer_name")
	private String issuerName;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private List<Tender> tenders = new ArrayList<>();

}
