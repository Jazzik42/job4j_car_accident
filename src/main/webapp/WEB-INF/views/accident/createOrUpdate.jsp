<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<form:form action="save" modelAttribute="accident">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Название:
            <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>Содержание:
            <form:input path="text" />
            </td>
        </tr>
        <tr>
            <td>Адрес:
            <form:input path="address" />
            </td>
        </tr>
        <tr>
            <td>Тип:
                <select name="type.id">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>
