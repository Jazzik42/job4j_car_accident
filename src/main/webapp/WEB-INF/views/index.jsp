<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title>Accident</title>
</head>
<body>

<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Accidents
            </div>
            <div class="card-body">
                <table class="table">
                    <tbody>
                    <c:forEach items="${accidents}" var="acc">
                        <c:url var="editButton" value ="/edit">
                            <c:param name="accId" value="${acc.id}"/>
                        </c:url>
                        <tr>
                            <td><c:out value="${acc.name}"/></td>
                            <td><c:out value="${acc.text}"/></td>
                            <td><c:out value="${acc.address}"/></td>
                            <td><c:out value="${acc.type.name}"/></td>
                            <td>
                            <table class="table">
                                <tbody>
                            <c:forEach items="${acc.rules}" var="rule">
                                <tr>
                                    <td><c:out value="${rule.name}"/></td>
                                </tr>
                            </c:forEach>
                                </tbody>
                            </table>
                            </td>
                            <td>
                                <input type="button" value="Update"
                                    onClick = "window.location.href = '${editButton}'">
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
                <a href="<c:url value='/create'/>">Добавить инцидент</a>
            </div>
        </div>
        </div>
    </div>
</body>
</html>