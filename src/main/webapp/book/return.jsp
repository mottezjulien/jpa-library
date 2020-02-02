<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Retourner un livre</title>
</head>
<body>
    <c:out value="${ requestScope.message }" /><br/>
    <form action="/library/book/return" method="GET">
        <span>Choisir le livre à retourner: </span>
        <select name="selectedBookId">
            <c:forEach items="${ requestScope.books }" var="book">
                <option value="${ book.id }"><c:out value="${ book.reference }: ${ book.details.title }" /></option>
            </c:forEach>
        </select><br/>
        <span>Choisir la bibliothèque: </span>
        <select  name="selectedLibraryId">
            <c:forEach items="${ requestScope.libraries }" var="library">
                <option value="${ library.id }"><c:out value="${ library.name }" /></option>
            </c:forEach>
        </select>
        <input type="submit" value="valider" />
    </form>
    <a href="/library/">Accueil</a>
</body>
</html>