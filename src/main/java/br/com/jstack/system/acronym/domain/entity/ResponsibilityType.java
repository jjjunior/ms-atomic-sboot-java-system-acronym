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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message = "The name must not be blank.")
	@Size(max = 100, message = "The name must be at most 100 characters.")
	private String name;
	
	@NotBlank(message = "The description must not be blank.")
	@Size(max = 255, message = "The description must be at most 255 characters.")
	private String description;
	
	@NotNull(message = "The active status must not be null.")
	private Boolean active;
	
	@Embedded
	private Audit audit = new Audit();
	
}




