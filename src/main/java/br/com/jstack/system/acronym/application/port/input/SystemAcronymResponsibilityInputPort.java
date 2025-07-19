package br.com.jstack.system.acronym.application.port.input;

import java.util.ArrayList;
import java.util.List;

import br.com.jstack.system.acronym.application.port.output.SystemAcronymResponsibilityOutputPort;
import br.com.jstack.system.acronym.application.usecase.CreateUseCase;
import br.com.jstack.system.acronym.application.usecase.DeleteByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveAllUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.UpdateUseCase;
import br.com.jstack.system.acronym.domain.entity.SystemAcronymResponsibility;
import br.com.jstack.system.acronym.domain.policy.PolicyResolver;
import br.com.jstack.system.acronym.domain.policy.ValidationPolicy;
import br.com.jstack.system.acronym.domain.vo.OperationType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemAcronymResponsibilityInputPort implements CreateUseCase<SystemAcronymResponsibility>,
	RetrieveByIdUseCase<SystemAcronymResponsibility, Long>,
	RetrieveAllUseCase<SystemAcronymResponsibility>,
	UpdateUseCase<SystemAcronymResponsibility>,
	DeleteByIdUseCase<SystemAcronymResponsibility, Long> {
	
	private final SystemAcronymResponsibilityOutputPort       outputPort;
	private final PolicyResolver<SystemAcronymResponsibility> policyResolver;
	
	@Override
	public SystemAcronymResponsibility create(@Valid SystemAcronymResponsibility domain) {
		ValidationPolicy<SystemAcronymResponsibility> policy = policyResolver.resolve(OperationType.CREATE, SystemAcronymResponsibility.class);
		policy.validate(domain, OperationType.CREATE);
		return outputPort.save(domain);
	}
	
	@Override
	public SystemAcronymResponsibility retrieveById(Long id) {
		return outputPort.findById(id);
	}
	
	@Override
	public List<SystemAcronymResponsibility> retrieveAll() {
		return new ArrayList<>(outputPort.findAll());
	}
	
	@Override
	public SystemAcronymResponsibility update(@Valid SystemAcronymResponsibility domain) {
		ValidationPolicy<SystemAcronymResponsibility> policy = policyResolver.resolve(OperationType.UPDATE, SystemAcronymResponsibility.class);
		policy.validate(domain, OperationType.UPDATE);
		return outputPort.update(domain);
	}
	
	@Override
	public void deleteById(Long id) {
		outputPort.deleteById(id);
	}
}
