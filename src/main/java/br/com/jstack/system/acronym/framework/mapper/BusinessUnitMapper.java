package br.com.jstack.system.acronym.framework.mapper;

import br.com.jstack.system.acronym.domain.entity.BusinessUnit;
import br.com.jstack.system.acronym.model.BusinessUnitRequest;
import br.com.jstack.system.acronym.model.BusinessUnitResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessUnitMapper {
	
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "businessDomains", ignore = true)
	@Mapping(target = "audit", ignore = true)
	@Mapping(target = "name", source = "name")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "active", source = "active")
	BusinessUnit toDomain(BusinessUnitRequest request);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "active", source = "active")
	@Mapping(target = "createdBy", source = "audit.createdBy")
	@Mapping(target = "createdAt", source = "audit.createdAt")
	@Mapping(target = "updatedBy", source = "audit.updatedBy")
	@Mapping(target = "updatedAt", source = "audit.updatedAt")
	BusinessUnitResponse toResponse(BusinessUnit domain);
	
}
