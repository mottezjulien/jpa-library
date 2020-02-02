package fr.greta.java.book.facade;

import fr.greta.java.library.persistence.repository.LibraryBorrowedBookPhysicRepository;
import fr.greta.java.library.persistence.repository.LibraryRepository;
import fr.greta.java.library.persistence.repository.LibraryReturnedBookPhysicRepository;
import fr.greta.java.library.persistence.entity.LibraryBorrowedBookPhysicEntity;
import fr.greta.java.library.persistence.entity.LibraryEntity;

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

@WebServlet("/book/return")
public class BookReturnServlet extends HttpServlet {

    private static final String ATTRIBUTE_KEY_BOOKS = "books";
    private static final String ATTRIBUTE_KEY_LIBRARIES = "libraries";
    private static final String ATTRIBUTE_KEY_MESSAGE = "message";

    private static final String PARAMETER_KEY_RETURNED_BOOK = "selectedBookId";
    private static final String PARAMETER_KEY_RETURNED_LIBRARY = "selectedLibraryId";

    private static String PERSISTENCE = "jpa-unit";

    private EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory(PERSISTENCE);

    private EntityManager entityManager;

    private LibraryReturnedBookPhysicRepository returnedRepository;
    private LibraryBorrowedBookPhysicRepository borrowedRepository;
    private LibraryRepository libraryRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        entityManager = entityFactory.createEntityManager();
        returnedRepository = new LibraryReturnedBookPhysicRepository(entityManager);
        borrowedRepository = new LibraryBorrowedBookPhysicRepository(entityManager);
        libraryRepository = new LibraryRepository(entityManager);
    }

    @Override
    public void destroy() {
        super.destroy();
        entityManager.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isReturnAction(req)) {
            returnAction(req, resp);
        } else {
            displayReturnForm(req, resp);
        }
    }

    private boolean isReturnAction(HttpServletRequest req) {
        return req.getParameter(PARAMETER_KEY_RETURNED_BOOK) != null
                && req.getParameter(PARAMETER_KEY_RETURNED_LIBRARY) != null;
    }

    private void returnAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String returnedBook = req.getParameter(PARAMETER_KEY_RETURNED_BOOK);
        String returnedLibrary = req.getParameter(PARAMETER_KEY_RETURNED_LIBRARY);

        LibraryBorrowedBookPhysicEntity book = borrowedRepository.findById(Integer.parseInt(returnedBook));
        LibraryEntity library = libraryRepository.findById(Integer.parseInt(returnedLibrary));

        returnedRepository.update(book, library);

        req.setAttribute(ATTRIBUTE_KEY_MESSAGE, "Le livre a bien été retourné");

        displayReturnForm(req, resp);
    }

    private void displayReturnForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(ATTRIBUTE_KEY_BOOKS, borrowedRepository.findAll());
        req.setAttribute(ATTRIBUTE_KEY_LIBRARIES, libraryRepository.findAll());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/book/return.jsp");
        dispatcher.forward(req, resp);
    }


}
