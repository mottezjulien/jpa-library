package fr.greta.java.library.persistence.repository;

import fr.greta.java.generic.persistence.GenericRepository;
import fr.greta.java.library.persistence.entity.LibraryBorrowedBookPhysicEntity;
import fr.greta.java.library.persistence.entity.LibraryEntity;
import fr.greta.java.library.persistence.entity.LibraryStoredBookPhysicEntity;
import fr.greta.java.user.persistence.entity.UserEntity;

import javax.persistence.EntityManager;

public class LibraryRepository extends GenericRepository<LibraryEntity> {

    public LibraryRepository(EntityManager entityManager) {
        super(entityManager, LibraryEntity.class);
    }

}
