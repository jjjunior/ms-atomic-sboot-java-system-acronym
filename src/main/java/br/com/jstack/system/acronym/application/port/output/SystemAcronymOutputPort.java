package br.com.jstack.system.acronym.application.port.output;

import br.com.jstack.system.acronym.domain.entity.SystemAcronym;

public interface SystemAcronymOutputPort extends PersistencePort<SystemAcronym, Long> {
	boolean existsByName(String name);
	
	boolean existsByNameAndIdNot(String name, Long id);
}
