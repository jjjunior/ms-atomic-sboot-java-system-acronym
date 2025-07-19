package br.com.jstack.system.acronym.application.port.output;

import br.com.jstack.system.acronym.domain.entity.ResponsibilityType;

public interface ResponsibilityTypeOutputPort extends PersistencePort<ResponsibilityType, Long> {
	boolean existsByName(String name);
	
	boolean existsByNameAndIdNot(String name, Long id);
}
