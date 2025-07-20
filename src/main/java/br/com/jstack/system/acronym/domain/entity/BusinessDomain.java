package br.com.jstack.system.acronym.domain.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.jstack.system.acronym.domain.vo.Audit;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "business_domain")
public class BusinessDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_domain_seq")
	@SequenceGenerator(name = "business_domain_seq", sequenceName = "business_domain_business_domain_id_seq", allocationSize = 1)
	@Column(name = "business_domain_id")
	private Long id;
	
	@NotBlank(message = "Name cannot be blank")
	@Column(nullable = false, length = 100)
	private String name;
	
	@NotBlank(message = "Description cannot be blank")
	@Column(nullable = false)
	private String description;
	
	@NotNull(message = "Active status cannot be null")
	@Column(nullable = false)
	private Boolean active;
	
	@Embedded
	private Audit audit = new Audit();
	
	@ManyToMany(mappedBy = "businessDomains", fetch = FetchType.LAZY)
	private List<BusinessUnit> businessUnits = new ArrayList<>();
	
	@OneToMany(mappedBy = "businessDomain", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<BusinessUnitDomain> businessUnitDomains = new ArrayList<>();
}