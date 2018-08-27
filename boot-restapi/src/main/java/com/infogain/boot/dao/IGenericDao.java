package com.infogain.boot.dao;

import com.infogain.boot.util.IOperations;

public interface IGenericDao<T> extends IOperations<T> {

	T find(T entity, Long id, String condition);

}
