package com.rtkit.fifth.element.kms.model.mapper;

import java.util.List;

public interface EntityMapper <D, E> {
    public D modelToDto(E entity);
    public List <D> modelToDto(List<E> entityList);
}