package br.com.jstack.system.acronym.framework.mapper;

import br.com.jstack.system.acronym.domain.entity.BusinessUnit;
import br.com.jstack.system.acronym.model.BusinessUnitRequest;
import br.com.jstack.system.acronym.model.BusinessUnitResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessUnitMapper {
	
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "businessUnitDomains", ignore = true)
	@Mapping(target = "businessDomains", ignore = true)
	@Mapping(target = "audit", ignore = true)
	BusinessUnit toDomain(BusinessUnitRequest request);
	
	@Mapping(target = "businessUnit.name", source = "name")
	@Mapping(target = "businessUnit.description", source = "description")
	@Mapping(target = "businessUnit.active", source = "active")
	BusinessUnitResponse toResponse(BusinessUnit domain);
	
}
