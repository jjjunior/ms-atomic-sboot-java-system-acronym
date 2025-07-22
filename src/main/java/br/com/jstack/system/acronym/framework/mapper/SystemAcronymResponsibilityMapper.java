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
	SystemAcronymResponsibility toDomain(SystemAcronymResponsibilityRequest request);
	
	@Mapping(target = "systemAcronymResponsibility.name", source = "name")
	@Mapping(target = "systemAcronymResponsibility.description", source = "description")
	@Mapping(target = "systemAcronymResponsibility.acronymId", source = "acronym.id")
	@Mapping(target = "systemAcronymResponsibility.responsibilityId", source = "responsibilityType.id")
	@Mapping(target = "systemAcronymResponsibility.teamId", source = "team.id")
	@Mapping(target = "systemAcronymResponsibility.active", source = "active")
	SystemAcronymResponsibilityResponse toResponse(SystemAcronymResponsibility domain);
}
