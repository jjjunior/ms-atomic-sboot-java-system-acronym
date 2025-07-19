package br.com.jstack.system.acronym.application.port.output;

import br.com.jstack.system.acronym.domain.entity.BusinessUnit;

public interface BusinessUnitOutputPort extends PersistencePort<BusinessUnit, Long> {
	boolean existsByName(String name);
	
	boolean existsByNameAndIdNot(String name, Long id);
}
