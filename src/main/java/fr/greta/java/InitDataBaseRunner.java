package fr.greta.java;

import fr.greta.java.book.persistence.BookAuthorEntity;
import fr.greta.java.book.persistence.BookDetailsEntity;
import fr.greta.java.library.persistence.entity.*;
import fr.greta.java.user.persistence.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InitDataBaseRunner {

    private static String PERSISTENCE = "jpa-unit";

    private static EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory(PERSISTENCE);

    public static void main(String[] arg) {
        EntityManager entityManager = entityFactory.createEntityManager();


        entityManager.getTransaction().begin();

        LibraryEntity lib0 = new LibraryEntity();
        lib0.setName("Librairie du centre");
        entityManager.persist(lib0);

        LibraryEntity lib1 = new LibraryEntity();
        lib1.setName("Librairie du quartier");
        entityManager.persist(lib1);

        LibraryEntity lib2 = new LibraryEntity();
        lib2.setName("Lib-bus");
        entityManager.persist(lib2);


        LibraryShelfEntity shelf0 = new LibraryShelfEntity();
        shelf0.setLabel("Couloir Nord");
        shelf0.setLibrary(lib0);
        entityManager.persist(shelf0);

        LibraryShelfEntity shelf1 = new LibraryShelfEntity();
        shelf1.setLabel("Allée centrale");
        shelf1.setLibrary(lib0);
        entityManager.persist(shelf1);

        LibraryShelfEntity shelf2 = new LibraryShelfEntity();
        shelf2.setLabel("Etage 0, Salle 1, Etagère 2");
        shelf2.setLibrary(lib1);
        entityManager.persist(shelf2);

        LibraryShelfEntity shelf3 = new LibraryShelfEntity();
        shelf3.setLabel("Etage 0, Salle 2, Etagère 3");
        shelf3.setLibrary(lib1);
        entityManager.persist(shelf3);

        LibraryShelfEntity shelf4 = new LibraryShelfEntity();
        shelf4.setLabel("Etage 2, Salle 1, Etagère 1");
        shelf4.setLibrary(lib1);
        entityManager.persist(shelf4);

        LibraryShelfEntity shelf5 = new LibraryShelfEntity();
        shelf5.setLabel("Fond du bus");
        shelf5.setLibrary(lib2);
        entityManager.persist(shelf5);

        BookAuthorEntity author0 = new BookAuthorEntity();
        author0.setFirstName("Michel");
        author0.setLastName("Dupont");
        entityManager.persist(author0);

        BookAuthorEntity author1 = new BookAuthorEntity();
        author1.setFirstName("Alice");
        author1.setLastName("Durant");
        entityManager.persist(author1);

        BookDetailsEntity details0 = new BookDetailsEntity();
        details0.setAuthor(author0);
        details0.setTitle("La rose et la chouette");
        details0.setDescription("Très beau livre d'actualité");
        entityManager.persist(details0);

        BookDetailsEntity details1 = new BookDetailsEntity();
        details1.setAuthor(author0);
        details1.setTitle("Et si je partais loin");
        details1.setDescription("C'est l'histoire de Pierre qui rencontre Brigitte");
        entityManager.persist(details1);

        BookDetailsEntity details2 = new BookDetailsEntity();
        details2.setAuthor(author1);
        details2.setTitle("L'horreur à la maison");
        details2.setDescription("Cela va te glacer le sang !!");
        entityManager.persist(details2);

        BookDetailsEntity details3 = new BookDetailsEntity();
        details3.setAuthor(author1);
        details3.setTitle("L'horreur à la maison 3-bis");
        details3.setDescription("Le retour du glaçon");
        entityManager.persist(details3);

        BookDetailsEntity details4 = new BookDetailsEntity();
        details4.setAuthor(author1);
        details4.setTitle("L'horreur à la maison 2");
        details4.setDescription("Le retour du retour du glaçon");
        entityManager.persist(details4);

        UserEntity user0 = new UserEntity();
        user0.setFirstName("Brice");
        entityManager.persist(user0);

        UserEntity user1 = new UserEntity();
        user1.setFirstName("Charlène");
        entityManager.persist(user1);

        LibraryBorrowedBookPhysicEntity borrowBook0 = new LibraryBorrowedBookPhysicEntity();
        borrowBook0.setReference("AAA01");
        borrowBook0.setDetails(details0);
        borrowBook0.setUser(user0);
        entityManager.persist(borrowBook0);

        LibraryBorrowedBookPhysicEntity borrowBook1 = new LibraryBorrowedBookPhysicEntity();
        borrowBook1.setReference("EF410");
        borrowBook1.setDetails(details1);
        borrowBook1.setUser(user1);
        entityManager.persist(borrowBook1);

        LibraryReturnedBookPhysicEntity returnBook0 = new LibraryReturnedBookPhysicEntity();
        returnBook0.setReference("CCC01");
        returnBook0.setDetails(details2);
        returnBook0.setLibrary(lib0);
        entityManager.persist(returnBook0);

        LibraryReturnedBookPhysicEntity returnBook1 = new LibraryReturnedBookPhysicEntity();
        returnBook1.setReference("EF411");
        returnBook1.setDetails(details1);
        returnBook1.setLibrary(lib0);
        entityManager.persist(returnBook1);

        LibraryStoredBookPhysicEntity storedBook0 = new LibraryStoredBookPhysicEntity();
        storedBook0.setReference("EF412");
        storedBook0.setDetails(details1);
        storedBook0.setShelf(shelf4);
        entityManager.persist(storedBook0);

        LibraryStoredBookPhysicEntity storedBook1 = new LibraryStoredBookPhysicEntity();
        storedBook1.setReference("DF001");
        storedBook1.setDetails(details2);
        storedBook1.setShelf(shelf2);
        entityManager.persist(storedBook1);

        LibraryStoredBookPhysicEntity storedBook3 = new LibraryStoredBookPhysicEntity();
        storedBook3.setReference("te025");
        storedBook3.setDetails(details4);
        storedBook3.setShelf(shelf5);
        entityManager.persist(storedBook3);


        entityManager.getTransaction().commit();

    }
}
