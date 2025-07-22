package br.com.jstack.system.acronym.domain.entity;

import br.com.jstack.system.acronym.domain.vo.Audit;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@Table(name = "responsibility_type")
public class ResponsibilityType {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsibility_type_id_seq")
	@SequenceGenerator(name = "responsibility_type_id_seq", sequenceName = "responsibility_type_responsibility_type_id_seq", allocationSize = 1)
	@Column(name = "responsibility_type_id")
	private Long id;
	
	private String name;
	
	private String description;
	
	private Boolean active;
	
	@Embedded
	private Audit audit = new Audit();
	
}




