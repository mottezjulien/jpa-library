package fr.greta.java.library.persistence.entity;


import fr.greta.java.user.persistence.entity.UserEntity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("BORROWED")
public class LibraryBorrowedBookPhysicEntity extends LibraryAbstractBookPhysicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
