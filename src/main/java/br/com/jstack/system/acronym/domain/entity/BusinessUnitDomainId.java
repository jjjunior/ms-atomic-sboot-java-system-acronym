package br.com.jstack.system.acronym.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUnitDomainId {
	
	@Column(name = "business_unit_id")
	private Long businessUnitId;
	
	@Column(name = "business_domain_id")
	private Long businessDomainId;
}