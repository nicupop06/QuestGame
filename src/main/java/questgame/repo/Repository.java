package questgame.repo;

import questgame.domain.Entity;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in repository
 */

public interface Repository<ID, E extends Entity<ID>> {

    E findOne(ID id);
    Iterable<E> findAll();
    void save(E entity);
}
