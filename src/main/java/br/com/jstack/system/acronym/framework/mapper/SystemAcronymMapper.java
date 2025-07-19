package br.com.jstack.system.acronym.framework.mapper;

import br.com.jstack.system.acronym.domain.entity.SystemAcronym;
import br.com.jstack.system.acronym.model.SystemAcronymRequest;
import br.com.jstack.system.acronym.model.SystemAcronymResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SystemAcronymMapper {
	
	@Mapping(target = "type.id", source = "typeId")
	@Mapping(target = "responsibilities", ignore = true)
	@Mapping(target = "domain.id", source = "domainId")
	@Mapping(target = "devTeam.id", source = "devTeamId")
	@Mapping(target = "bizTeam.id", source = "bizTeamId")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "audit", ignore = true)
	@Mapping(target = "name", source = "name")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "active", source = "active")
	SystemAcronym toDomain(SystemAcronymRequest request);
	
	
	@Mapping(target = "typeId", source = "type.id")
	@Mapping(target = "domainId", source = "domain.id")
	@Mapping(target = "devTeamId", source = "devTeam.id")
	@Mapping(target = "bizTeamId", source = "bizTeam.id")
	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "active", source = "active")
	@Mapping(target = "createdBy", source = "audit.createdBy")
	@Mapping(target = "createdAt", source = "audit.createdAt")
	@Mapping(target = "updatedBy", source = "audit.updatedBy")
	@Mapping(target = "updatedAt", source = "audit.updatedAt")
	SystemAcronymResponse toResponse(SystemAcronym domain);
}
