package br.com.jstack.system.acronym.application.port.input;

import java.util.ArrayList;
import java.util.List;

import br.com.jstack.system.acronym.application.port.output.SystemAcronymOutputPort;
import br.com.jstack.system.acronym.application.usecase.CreateUseCase;
import br.com.jstack.system.acronym.application.usecase.DeleteByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveAllUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.UpdateUseCase;
import br.com.jstack.system.acronym.domain.entity.SystemAcronym;
import br.com.jstack.system.acronym.domain.policy.PolicyResolver;
import br.com.jstack.system.acronym.domain.policy.ValidationPolicy;
import br.com.jstack.system.acronym.domain.vo.OperationType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemAcronymInputPort implements
	CreateUseCase<SystemAcronym>,
	RetrieveByIdUseCase<SystemAcronym, Long>,
	RetrieveAllUseCase<SystemAcronym>,
	UpdateUseCase<SystemAcronym>,
	DeleteByIdUseCase<SystemAcronym, Long> {
	
	private final SystemAcronymOutputPort       outputPort;
	private final PolicyResolver<SystemAcronym> policyResolver;
	
	@Override
	public SystemAcronym create(@Valid SystemAcronym domain) {
		ValidationPolicy<SystemAcronym> policy = policyResolver.resolve(OperationType.CREATE, SystemAcronym.class);
		policy.validate(domain, OperationType.CREATE);
		return outputPort.save(domain);
	}
	
	@Override
	public SystemAcronym retrieveById(Long id) {
		return outputPort.findById(id);
	}
	
	@Override
	public List<SystemAcronym> retrieveAll() {
		return new ArrayList<>(outputPort.findAll());
	}
	
	@Override
	public SystemAcronym update(@Valid SystemAcronym domain) {
		ValidationPolicy<SystemAcronym> policy = policyResolver.resolve(OperationType.UPDATE, SystemAcronym.class);
		policy.validate(domain, OperationType.UPDATE);
		return outputPort.update(domain);
	}
	
	@Override
	public void deleteById(Long id) {
		outputPort.deleteById(id);
	}
}
