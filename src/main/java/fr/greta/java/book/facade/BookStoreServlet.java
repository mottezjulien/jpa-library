package fr.greta.java.book.facade;


import fr.greta.java.library.persistence.entity.LibraryReturnedBookPhysicEntity;
import fr.greta.java.library.persistence.repository.LibraryReturnedBookPhysicRepository;
import fr.greta.java.library.persistence.repository.LibraryShelfRepository;
import fr.greta.java.library.persistence.repository.LibraryStoredBookPhysicRepository;
import fr.greta.java.library.persistence.entity.LibraryBorrowedBookPhysicEntity;
import fr.greta.java.library.persistence.entity.LibraryShelfEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book/store")
public class BookStoreServlet extends HttpServlet {

    private static final String ATTRIBUTE_KEY_BOOKS = "books";
    private static final String ATTRIBUTE_KEY_SHELVES = "shelves";
    private static final String ATTRIBUTE_KEY_MESSAGE = "message";

    private static final String PARAMETER_KEY_BOOK = "selectedBookId";
    private static final String PARAMETER_KEY_SHELF = "selectedShelfId";

    private static String PERSISTENCE = "jpa-unit";

    private EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory(PERSISTENCE);

    private EntityManager entityManager;
    private LibraryReturnedBookPhysicRepository returnedRepository;
    private LibraryStoredBookPhysicRepository storedRepository;
    private LibraryShelfRepository shelfRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        entityManager = entityFactory.createEntityManager();
        returnedRepository = new LibraryReturnedBookPhysicRepository(entityManager);
        storedRepository = new LibraryStoredBookPhysicRepository(entityManager);
        shelfRepository = new LibraryShelfRepository(entityManager);
    }

    @Override
    public void destroy() {
        super.destroy();
        entityManager.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isStoreAction(req)) {
            storeAction(req, resp);
        } else {
            displayStoreForm(req, resp);
        }
    }



    private boolean isStoreAction(HttpServletRequest req) {
        return req.getParameter(PARAMETER_KEY_BOOK) != null
                && req.getParameter(PARAMETER_KEY_SHELF) != null;
    }

    private void storeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String storeBook = req.getParameter(PARAMETER_KEY_BOOK);
        String storeShelf = req.getParameter(PARAMETER_KEY_SHELF);

        LibraryReturnedBookPhysicEntity book = returnedRepository.findById(Integer.parseInt(storeBook));
        LibraryShelfEntity shelf = shelfRepository.findById(Integer.parseInt(storeShelf));

        storedRepository.update(book, shelf);

        req.setAttribute(ATTRIBUTE_KEY_MESSAGE, "Le livre a bien été rangé");

        displayStoreForm(req, resp);
    }

    private void displayStoreForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ATTRIBUTE_KEY_BOOKS, returnedRepository.findAll());
        req.setAttribute(ATTRIBUTE_KEY_SHELVES, shelfRepository.findAll());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/book/store.jsp");
        dispatcher.forward(req, resp);
    }






}
