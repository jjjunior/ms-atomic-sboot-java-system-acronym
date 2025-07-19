package br.com.jstack.system.acronym.domain.policy;

import br.com.jstack.system.acronym.application.port.output.SystemAcronymResponsibilityOutputPort;
import br.com.jstack.system.acronym.domain.entity.SystemAcronymResponsibility;
import br.com.jstack.system.acronym.domain.specification.Specification;
import br.com.jstack.system.acronym.domain.specification.SpecificationFactory;
import br.com.jstack.system.acronym.domain.vo.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemAcronymResponsibilityPolicy implements ValidationPolicy<SystemAcronymResponsibility> {
	
	private final SpecificationFactory                  specFactory;
	private final SystemAcronymResponsibilityOutputPort outputPort;
	
	@Override
	public void validate(SystemAcronymResponsibility domain, OperationType operation) {
		
		if (operation == OperationType.CREATE) {
			Specification<SystemAcronymResponsibility> uniqueNameSpec = specFactory.uniqueName(outputPort::existsByName, SystemAcronymResponsibility::getName);
			if (!uniqueNameSpec.isSatisfiedBy(domain)) {
				throw new IllegalArgumentException("System Acronym Type must be unique.");
			}
		}
		
		if (operation == OperationType.UPDATE) {
			Specification<SystemAcronymResponsibility> uniqueNameExclIdSpec = specFactory.uniqueNameExcludingSelf(outputPort::existsByNameAndIdNot, SystemAcronymResponsibility::getName, SystemAcronymResponsibility::getId);
			if (!uniqueNameExclIdSpec.isSatisfiedBy(domain)) {
				throw new IllegalArgumentException("System Acronym Type must be unique (excluding self).");
			}
		}
	}
	
	@Override
	public boolean supports(OperationType operation) {
		return operation == OperationType.CREATE || operation == OperationType.UPDATE;
	}
	
	@Override
	public Class<SystemAcronymResponsibility> getTargetType() {
		return SystemAcronymResponsibility.class;
	}
}