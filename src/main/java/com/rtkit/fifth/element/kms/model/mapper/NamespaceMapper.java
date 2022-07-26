package com.rtkit.fifth.element.kms.model.mapper;

import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.entity.Namespace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NamespaceMapper extends EntityMapper<NamespaceDto, Namespace> {

    @Mapping(source = "namespace.creator.name", target = "creator")
    List<NamespaceDto> modelToDto(List<Namespace> namespace);
}
