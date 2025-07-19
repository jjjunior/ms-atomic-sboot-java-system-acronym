package br.com.jstack.system.acronym.framework.adapter.input.rest;

import java.util.List;

import br.com.jstack.system.acronym.api.SystemAcronymApi;
import br.com.jstack.system.acronym.application.usecase.CreateUseCase;
import br.com.jstack.system.acronym.application.usecase.DeleteByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveAllUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.UpdateUseCase;
import br.com.jstack.system.acronym.domain.entity.SystemAcronym;
import br.com.jstack.system.acronym.framework.mapper.SystemAcronymMapper;
import br.com.jstack.system.acronym.model.SystemAcronymRequest;
import br.com.jstack.system.acronym.model.SystemAcronymResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SystemAcronymRestAdapter implements SystemAcronymApi {
	
	private final SystemAcronymMapper                      mapper;
	private final CreateUseCase<SystemAcronym>             createUseCase;
	private final RetrieveByIdUseCase<SystemAcronym, Long> retrieveByIdUseCase;
	private final RetrieveAllUseCase<SystemAcronym>        retrieveAllUseCase;
	private final UpdateUseCase<SystemAcronym>             updateUseCase;
	private final DeleteByIdUseCase<SystemAcronym, Long>   deleteUseCase;
	
	@Override
	public ResponseEntity<SystemAcronymResponse> createSystemAcronym(SystemAcronymRequest request) {
		SystemAcronym         created  = createUseCase.create(mapper.toDomain(request));
		SystemAcronymResponse response = mapper.toResponse(created);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@Override
	public ResponseEntity<Void> deleteSystemAcronym(Long id) {
		deleteUseCase.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@Override
	public ResponseEntity<List<SystemAcronymResponse>> listSystemAcronyms(Boolean active, Long typeId, Long domainId) {
		List<SystemAcronym>         systemAcronymTypes = retrieveAllUseCase.retrieveAll();
		List<SystemAcronymResponse> responses          = systemAcronymTypes.stream().map(mapper::toResponse).toList();
		return ResponseEntity.status(HttpStatus.OK).body(responses);
		
	}
	
	@Override
	public ResponseEntity<SystemAcronymResponse> retrieveSystemAcronym(Long id) {
		SystemAcronym         retrieveById = retrieveByIdUseCase.retrieveById(id);
		SystemAcronymResponse response     = mapper.toResponse(retrieveById);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@Override
	public ResponseEntity<SystemAcronymResponse> updateSystemAcronym(Long id, SystemAcronymRequest request) {
		SystemAcronym systemAcronymType = mapper.toDomain(request);
		systemAcronymType.setId(id);
		SystemAcronym         updated  = updateUseCase.update(systemAcronymType);
		SystemAcronymResponse response = mapper.toResponse(updated);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
