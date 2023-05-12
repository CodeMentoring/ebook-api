package com.codementoring.ebookapi.service.impl;

import com.codementoring.ebookapi.repository.IGenericRepository;
import com.codementoring.ebookapi.service.ICRUD;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }
}
