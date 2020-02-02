package fr.greta.java.book.facade;

import fr.greta.java.library.persistence.repository.LibraryBorrowedBookPhysicRepository;
import fr.greta.java.library.persistence.repository.LibraryStoredBookPhysicRepository;
import fr.greta.java.library.persistence.entity.LibraryStoredBookPhysicEntity;
import fr.greta.java.user.persistence.entity.UserEntity;
import fr.greta.java.user.persistence.entity.UserRepository;

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

@WebServlet("/book/borrow")
public class BookBorrowServlet extends HttpServlet {

    private static final String ATTRIBUTE_KEY_BOOKS = "books";
    private static final String ATTRIBUTE_KEY_USERS = "users";
    private static final String ATTRIBUTE_KEY_MESSAGE = "message";

    private static final String PARAMETER_KEY_BOOK = "selectedBookId";
    private static final String PARAMETER_KEY_USER = "selectedUserId";


    private static String PERSISTENCE = "jpa-unit";

    private EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory(PERSISTENCE);

    private EntityManager entityManager;

    private LibraryStoredBookPhysicRepository storedBookRepository;
    private UserRepository userRepository;
    private LibraryBorrowedBookPhysicRepository borrowedRepository;


    @Override
    public void init() throws ServletException {
        super.init();
        entityManager = entityFactory.createEntityManager();
        storedBookRepository = new LibraryStoredBookPhysicRepository(entityManager);
        borrowedRepository = new LibraryBorrowedBookPhysicRepository(entityManager);
        userRepository = new UserRepository(entityManager);
    }

    @Override
    public void destroy() {
        super.destroy();
        entityManager.close();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isBorrowAction(req)) {
            borrowAction(req, resp);
        } else {
            displayBorrowForm(req, resp);
        }
    }

    private boolean isBorrowAction(HttpServletRequest req) {
        return req.getParameter(PARAMETER_KEY_BOOK) != null
                && req.getParameter(PARAMETER_KEY_USER) != null;
    }

    private void borrowAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String borrowedBook = req.getParameter(PARAMETER_KEY_BOOK);
        String borrowedUser = req.getParameter(PARAMETER_KEY_USER);

        LibraryStoredBookPhysicEntity book = storedBookRepository.findById(Integer.parseInt(borrowedBook));
        UserEntity user = userRepository.findById(Integer.parseInt(borrowedUser));

        borrowedRepository.update(book, user);

        req.setAttribute(ATTRIBUTE_KEY_MESSAGE, "Le livre a bien été emprunté");

        displayBorrowForm(req, resp);
    }

    private void displayBorrowForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ATTRIBUTE_KEY_BOOKS, storedBookRepository.findAll());
        req.setAttribute(ATTRIBUTE_KEY_USERS, userRepository.findAll());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/book/borrow.jsp");
        dispatcher.forward(req, resp);
    }

}
