package br.com.jstack.system.acronym.framework.mapper;

import br.com.jstack.system.acronym.domain.entity.SystemAcronym;
import br.com.jstack.system.acronym.model.SystemAcronymRequest;
import br.com.jstack.system.acronym.model.SystemAcronymResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SystemAcronymMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "audit", ignore = true)
	@Mapping(target = "responsibilities", ignore = true)
	@Mapping(target = "type.id", source = "typeId")
	@Mapping(target = "domain.id", source = "domainId")
	@Mapping(target = "devTeam.id", source = "devTeamId")
	@Mapping(target = "bizTeam.id", source = "bizTeamId")
	SystemAcronym toDomain(SystemAcronymRequest request);
	
	@Mapping(target = "systemAcronym.name", source = "name")
	@Mapping(target = "systemAcronym.description", source = "description")
	@Mapping(target = "systemAcronym.active", source = "active")
	@Mapping(target = "systemAcronym.typeId", source = "type.id")
	@Mapping(target = "systemAcronym.domainId", source = "domain.id")
	@Mapping(target = "systemAcronym.devTeamId", source = "devTeam.id")
	@Mapping(target = "systemAcronym.bizTeamId", source = "bizTeam.id")
	SystemAcronymResponse toResponse(SystemAcronym domain);
}
