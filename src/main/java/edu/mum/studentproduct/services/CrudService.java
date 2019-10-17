package edu.mum.studentproduct.services;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jonathan on 10/16/2019.
 */

public interface CrudService<T, ID> {
	List<T> findAll();
	T findById(ID id);
	T save(T t);
}
