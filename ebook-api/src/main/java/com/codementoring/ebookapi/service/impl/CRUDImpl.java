package com.codementoring.ebookapi.service.impl;

import com.codementoring.ebookapi.exception.ModelNotFoundException;
import com.codementoring.ebookapi.repository.IGenericRepository;
import com.codementoring.ebookapi.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        return getRepo().save(t);
    }


    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }
}
