package fr.greta.java.library.persistence.repository;

import fr.greta.java.generic.persistence.GenericRepository;
import fr.greta.java.library.persistence.entity.LibraryShelfEntity;

import javax.persistence.EntityManager;

public class LibraryShelfRepository extends GenericRepository<LibraryShelfEntity> {

    public LibraryShelfRepository(EntityManager entityManager) {
        super(entityManager, LibraryShelfEntity.class);
    }

}
