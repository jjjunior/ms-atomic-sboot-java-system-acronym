package br.com.jstack.system.acronym.application.port.input;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jstack.system.acronym.application.port.output.SystemAcronymTypeOutputPort;
import br.com.jstack.system.acronym.application.usecase.CreateUseCase;
import br.com.jstack.system.acronym.application.usecase.DeleteByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveAllUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.UpdateUseCase;
import br.com.jstack.system.acronym.domain.entity.SystemAcronymType;
import br.com.jstack.system.acronym.domain.policy.PolicyResolver;
import br.com.jstack.system.acronym.domain.policy.ValidationPolicy;
import br.com.jstack.system.acronym.domain.vo.OperationType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemAcronymTypeInputPort implements CreateUseCase<SystemAcronymType>,
	RetrieveByIdUseCase<SystemAcronymType, Long>,
	RetrieveAllUseCase<SystemAcronymType>,
	UpdateUseCase<SystemAcronymType>,
	DeleteByIdUseCase<SystemAcronymType, Long> {
	
	private final SystemAcronymTypeOutputPort       outputPort;
	private final PolicyResolver<SystemAcronymType> policyResolver;
	
	@Override
	public SystemAcronymType create(@Valid SystemAcronymType domain) {
		ValidationPolicy<SystemAcronymType> policy = policyResolver.resolve(OperationType.CREATE, SystemAcronymType.class);
		policy.validate(domain, OperationType.CREATE);
		return outputPort.save(domain);
	}
	
	@Override
	public SystemAcronymType retrieveById(Long id) {
		return outputPort.findById(id);
	}
	
	@Override
	public List<SystemAcronymType> retrieveAll() {
		return outputPort.findAll().stream().collect(Collectors.toList());
	}
	
	@Override
	public SystemAcronymType update(@Valid SystemAcronymType domain) {
		ValidationPolicy<SystemAcronymType> policy = policyResolver.resolve(OperationType.UPDATE, SystemAcronymType.class);
		policy.validate(domain, OperationType.UPDATE);
		return outputPort.update(domain);
	}
	
	@Override
	public void deleteById(Long id) {
		outputPort.deleteById(id);
	}
}
