package br.com.jstack.system.acronym.application.port.output;

import br.com.jstack.system.acronym.domain.entity.SystemAcronymType;

public interface SystemAcronymTypeOutputPort extends PersistencePort<SystemAcronymType, Long> {
	boolean existsByName(String name);
	
	boolean existsByNameAndIdNot(String name, Long id);
}
