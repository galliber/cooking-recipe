package dao.infrastructure;

import java.util.Collection;
import java.util.Optional;

import dao.Identifiable;
import exceptions.EntityDoesNotExistException;
import exceptions.EntityExistsException;

public interface Repository<K,V extends Identifiable<K>> {
	Optional<V> findById(K id);
	Collection<V> findAll();
	V add(V entity) throws EntityExistsException ;
	V update(V entity) throws EntityDoesNotExistException;
	Optional<V> delete(K id);
}
