package br.com.jstack.system.acronym.framework.mapper;

import br.com.jstack.system.acronym.domain.entity.ResponsibilityType;
import br.com.jstack.system.acronym.model.ResponsibilityTypeRequest;
import br.com.jstack.system.acronym.model.ResponsibilityTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResponsibilityMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "audit", ignore = true)
	ResponsibilityType toDomain(ResponsibilityTypeRequest request);
	
	@Mapping(target = "responsibilityType.name", source = "name")
	@Mapping(target = "responsibilityType.description", source = "description")
	@Mapping(target = "responsibilityType.active", source = "active")
	ResponsibilityTypeResponse toResponse(ResponsibilityType domain);
	
}
