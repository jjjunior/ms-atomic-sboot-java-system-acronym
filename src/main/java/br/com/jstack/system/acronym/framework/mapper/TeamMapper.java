package br.com.jstack.system.acronym.framework.mapper;

import br.com.jstack.system.acronym.domain.entity.Team;
import br.com.jstack.system.acronym.model.TeamRequest;
import br.com.jstack.system.acronym.model.TeamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "audit", ignore = true)
	Team toDomain(TeamRequest request);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "team.name", source = "name")
	@Mapping(target = "team.description", source = "description")
	TeamResponse toResponse(Team domain);
}
