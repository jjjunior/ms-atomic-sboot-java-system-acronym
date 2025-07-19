package br.com.jstack.system.acronym.framework.adapter.input.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.jstack.system.acronym.api.BusinessDomainApi;
import br.com.jstack.system.acronym.application.usecase.CreateUseCase;
import br.com.jstack.system.acronym.application.usecase.DeleteByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveAllUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.UpdateUseCase;
import br.com.jstack.system.acronym.domain.entity.BusinessDomain;
import br.com.jstack.system.acronym.domain.entity.BusinessUnit;
import br.com.jstack.system.acronym.framework.mapper.BusinessDomainMapper;
import br.com.jstack.system.acronym.model.BusinessDomainRequest;
import br.com.jstack.system.acronym.model.BusinessDomainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BusinessDomainRestAdapter implements BusinessDomainApi {
	
	private final BusinessDomainMapper                      mapper;
	private final CreateUseCase<BusinessDomain>             createUseCase;
	private final RetrieveByIdUseCase<BusinessDomain, Long> retrieveByIdUseCase;
	private final RetrieveAllUseCase<BusinessDomain>        retrieveAllUseCase;
	private final UpdateUseCase<BusinessDomain>             updateUseCase;
	private final DeleteByIdUseCase<BusinessDomain, Long>   deleteUseCase;
	
	@Override
	public ResponseEntity<BusinessDomainResponse> createBusinessDomain(BusinessDomainRequest request) {
		BusinessDomain domain = mapper.toDomain(request);
		domain.setBusinessUnits(mapUnitIdsToEntities(request.getUnitIds()));
		
		BusinessDomain         created  = createUseCase.create(domain);
		BusinessDomainResponse response = mapper.toResponse(created);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@Override
	public ResponseEntity<Void> deleteBusinessDomain(Long id) {
		deleteUseCase.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<List<BusinessDomainResponse>> listBusinessDomains() {
		List<BusinessDomain> domains = retrieveAllUseCase.retrieveAll();
		List<BusinessDomainResponse> responses = domains.stream()
			.map(mapper::toResponse)
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(responses);
	}
	
	@Override
	public ResponseEntity<BusinessDomainResponse> retrieveBusinessDomain(Long id) {
		BusinessDomain         domain   = retrieveByIdUseCase.retrieveById(id);
		BusinessDomainResponse response = mapper.toResponse(domain);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<BusinessDomainResponse> updateBusinessDomain(Long id, BusinessDomainRequest request) {
		BusinessDomain domain = mapper.toDomain(request);
		domain.setId(id);
		
		BusinessDomain         updated  = updateUseCase.update(domain);
		BusinessDomainResponse response = mapper.toResponse(updated);
		
		return ResponseEntity.ok(response);
	}
	
	private List<BusinessUnit> mapUnitIdsToEntities(List<Long> unitIds) {
		return Optional.ofNullable(unitIds)
			.orElse(List.of())
			.stream()
			.map(id -> {
				BusinessUnit unit = new BusinessUnit();
				unit.setId(id);
				return unit;
			})
			.collect(Collectors.toList());
	}
}