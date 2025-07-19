package br.com.jstack.system.acronym.domain.entity;

import br.com.jstack.system.acronym.domain.vo.Audit;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "business_unit_domain")
public class BusinessUnitDomain {
	
	@EmbeddedId
	private BusinessUnitDomainId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("businessUnitId")
	@JoinColumn(name = "business_unit_id", nullable = false)
	private BusinessUnit businessUnit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("businessDomainId")
	@JoinColumn(name = "business_domain_id", nullable = false)
	private BusinessDomain businessDomain;
	
	@Embedded
	private Audit audit = new Audit();
}