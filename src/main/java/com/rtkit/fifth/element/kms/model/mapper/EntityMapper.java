package com.rtkit.fifth.element.kms.model.mapper;

import java.util.List;

public interface EntityMapper <D, E> {
    D modelToDto(E entity);
    List <D> modelToDto(List<E> entityList);
}