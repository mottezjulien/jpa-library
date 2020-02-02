package fr.greta.java.user.persistence.entity;

import fr.greta.java.generic.persistence.GenericRepository;

import javax.persistence.EntityManager;

public class UserRepository extends GenericRepository<UserEntity> {

    public UserRepository(EntityManager entityManager) {
        super(entityManager, UserEntity.class);
    }

}