package br.com.jstack.system.acronym.framework.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jstack.system.acronym.domain.entity.BusinessDomain;
import br.com.jstack.system.acronym.domain.entity.BusinessUnit;
import br.com.jstack.system.acronym.domain.entity.BusinessUnitDomain;
import br.com.jstack.system.acronym.model.BusinessDomainRequest;
import br.com.jstack.system.acronym.model.BusinessDomainResponse;
import br.com.jstack.system.acronym.model.BusinessUnitResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BusinessDomainMapper {
	
	@Mapping(target = "businessUnitDomains", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "audit", ignore = true)
	BusinessDomain toDomain(BusinessDomainRequest request);
	
	@Mapping(target = "updatedBy", source = "audit.updatedBy")
	@Mapping(target = "updatedAt", source = "audit.updatedAt")
	@Mapping(target = "createdBy", source = "audit.createdBy")
	@Mapping(target = "createdAt", source = "audit.createdAt")
	@Mapping(target = "businessUnits", source = "businessUnitDomains", qualifiedByName = "mapUnitsFromRelations")
	BusinessDomainResponse toResponse(BusinessDomain domain);
	
	@Named("mapUnitsFromRelations")
	default List<BusinessUnitResponse> mapUnitsFromRelations(List<BusinessUnitDomain> relations) {
		return relations.stream()
			.map(BusinessUnitDomain::getBusinessUnit)
			.map(this::toBusinessUnitResponse)
			.collect(Collectors.toList());
	}
	
	@Mapping(target = "updatedBy", source = "audit.updatedBy")
	@Mapping(target = "updatedAt", source = "audit.updatedAt")
	@Mapping(target = "createdBy", source = "audit.createdBy")
	@Mapping(target = "createdAt", source = "audit.createdAt")
	BusinessUnitResponse toBusinessUnitResponse(BusinessUnit businessUnit);
}