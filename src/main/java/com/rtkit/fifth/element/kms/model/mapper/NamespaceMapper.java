package com.rtkit.fifth.element.kms.model.mapper;

import com.rtkit.fifth.element.kms.model.dto.NamespaceDto;
import com.rtkit.fifth.element.kms.model.entity.Namespace;

import java.util.List;
import java.util.stream.Collectors;

//@Mapper(componentModel = "spring")
public interface NamespaceMapper extends EntityMapper<NamespaceDto, Namespace> {

    //    @Mapping(source = "namespace.creator.name", target = "creator")
    default List<NamespaceDto> modelToDto(List<Namespace> namespace) {
        return namespace.stream().map(o ->
                new NamespaceDto(o.getId(), o.getTitle(), o.getCreator().getId())).collect(Collectors.toList());
    }
}
