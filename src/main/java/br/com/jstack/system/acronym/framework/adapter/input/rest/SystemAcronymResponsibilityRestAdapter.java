package br.com.jstack.system.acronym.framework.adapter.input.rest;

import java.util.List;

import br.com.jstack.system.acronym.api.SystemAcronymResponsibilityApi;
import br.com.jstack.system.acronym.application.usecase.CreateUseCase;
import br.com.jstack.system.acronym.application.usecase.DeleteByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveAllUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.UpdateUseCase;
import br.com.jstack.system.acronym.domain.entity.SystemAcronymResponsibility;
import br.com.jstack.system.acronym.framework.mapper.SystemAcronymResponsibilityMapper;
import br.com.jstack.system.acronym.model.SystemAcronymResponsibilityRequest;
import br.com.jstack.system.acronym.model.SystemAcronymResponsibilityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SystemAcronymResponsibilityRestAdapter implements SystemAcronymResponsibilityApi {
	
	private final SystemAcronymResponsibilityMapper                      mapper;
	private final CreateUseCase<SystemAcronymResponsibility>             createUseCase;
	private final RetrieveByIdUseCase<SystemAcronymResponsibility, Long> retrieveByIdUseCase;
	private final RetrieveAllUseCase<SystemAcronymResponsibility>        retrieveAllUseCase;
	private final UpdateUseCase<SystemAcronymResponsibility>             updateUseCase;
	private final DeleteByIdUseCase<SystemAcronymResponsibility, Long>   deleteUseCase;
	
	@Override
	public ResponseEntity<SystemAcronymResponsibilityResponse> createSystemAcronymResponsibility(SystemAcronymResponsibilityRequest request) {
		SystemAcronymResponsibility         created  = createUseCase.create(mapper.toDomain(request));
		SystemAcronymResponsibilityResponse response = mapper.toResponse(created);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@Override
	public ResponseEntity<Void> deleteSystemAcronymResponsibility(Long id) {
		deleteUseCase.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	
	@Override
	public ResponseEntity<List<SystemAcronymResponsibilityResponse>> listSystemAcronymResponsibilities() {
		List<SystemAcronymResponsibility>         systemAcronymTypes = retrieveAllUseCase.retrieveAll();
		List<SystemAcronymResponsibilityResponse> responses          = systemAcronymTypes.stream().map(mapper::toResponse).toList();
		return ResponseEntity.status(HttpStatus.OK).body(responses);
	}
	
	@Override
	public ResponseEntity<SystemAcronymResponsibilityResponse> retrieveSystemAcronymResponsibility(Long id) {
		SystemAcronymResponsibility         retrieveById = retrieveByIdUseCase.retrieveById(id);
		SystemAcronymResponsibilityResponse response     = mapper.toResponse(retrieveById);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@Override
	public ResponseEntity<SystemAcronymResponsibilityResponse> updateSystemAcronymResponsibility(Long id, SystemAcronymResponsibilityRequest request) {
		SystemAcronymResponsibility systemAcronymType = mapper.toDomain(request);
		systemAcronymType.setId(id);
		SystemAcronymResponsibility         updated  = updateUseCase.update(systemAcronymType);
		SystemAcronymResponsibilityResponse response = mapper.toResponse(updated);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
