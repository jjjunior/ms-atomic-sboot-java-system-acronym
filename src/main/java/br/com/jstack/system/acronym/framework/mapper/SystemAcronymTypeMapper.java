package br.com.jstack.system.acronym.framework.mapper;

import br.com.jstack.system.acronym.domain.entity.SystemAcronymType;
import br.com.jstack.system.acronym.model.SystemAcronymTypeRequest;
import br.com.jstack.system.acronym.model.SystemAcronymTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SystemAcronymTypeMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "audit", ignore = true)
	SystemAcronymType toDomain(SystemAcronymTypeRequest request);
	
	@Mapping(target = "systemAcronymType.name", source = "name")
	@Mapping(target = "systemAcronymType.description", source = "description")
	@Mapping(target = "systemAcronymType.active", source = "active")
	SystemAcronymTypeResponse toResponse(SystemAcronymType domain);
}
