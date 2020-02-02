<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emprunter un livre</title>
</head>
<body>
    <c:out value="${ requestScope.message }" /><br/>
    <form action="/library/book/borrow" method="GET">
        <span>Choisir le livre à emprunter: </span>
        <select name="selectedBookId">
            <c:forEach items="${ requestScope.books }" var="book">
                <option value="${ book.id }"><c:out value="${ book.reference }: ${ book.details.title }" /></option>
            </c:forEach>
        </select><br/>
        <span>Choisir l'utilisateur: </span>
        <select  name="selectedUserId">
            <c:forEach items="${ requestScope.users }" var="user">
                <option value="${ user.id }"><c:out value="${ user.firstName }" /></option>
            </c:forEach>
        </select>
        <input type="submit" value="valider" />
    </form>
    <a href="/library/">Accueil</a>
</body>
</html>