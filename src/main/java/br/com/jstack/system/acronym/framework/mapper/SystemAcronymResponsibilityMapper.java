package br.com.jstack.system.acronym.framework.mapper;

import br.com.jstack.system.acronym.domain.entity.SystemAcronymResponsibility;
import br.com.jstack.system.acronym.model.SystemAcronymResponsibilityRequest;
import br.com.jstack.system.acronym.model.SystemAcronymResponsibilityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SystemAcronymResponsibilityMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "audit", ignore = true)
	@Mapping(target = "acronym.id", source = "acronymId")
	@Mapping(target = "responsibilityType.id", source = "responsibilityId")
	@Mapping(target = "team.id", source = "teamId")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "active", source = "active")
	SystemAcronymResponsibility toDomain(SystemAcronymResponsibilityRequest request);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "acronymId", source = "acronym.id")
	@Mapping(target = "responsibilityId", source = "responsibilityType.id")
	@Mapping(target = "teamId", source = "team.id")
	@Mapping(target = "active", source = "active")
	@Mapping(target = "createdBy", source = "audit.createdBy")
	@Mapping(target = "createdAt", source = "audit.createdAt")
	@Mapping(target = "updatedBy", source = "audit.updatedBy")
	@Mapping(target = "updatedAt", source = "audit.updatedAt")
	SystemAcronymResponsibilityResponse toResponse(SystemAcronymResponsibility domain);
}
