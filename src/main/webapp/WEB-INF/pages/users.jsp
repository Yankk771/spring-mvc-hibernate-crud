<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Users</title>
</head>
<body>

<h2>All Users</h2>

<table border="1">

    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Age</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>

    <c:forEach var="user" items="${users}">

        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>
                <form action="/delete" method="post">

                    <input type="hidden"
                           name="id"
                           value="${user.id}">

                    <input type="submit"
                           value="Delete">

                </form>
            </td>
            <td>

                <form action="/edit" method="get">

                    <input type="hidden"
                           name="id"
                           value="${user.id}">

                    <input type="submit"
                           value="Edit">

                </form>

            </td>
        </tr>

    </c:forEach>

</table>


<h2>Add User</h2>

<form:form action="/add" method="post" modelAttribute="user">

    Name:
    <form:input path="name"/>
    <form:errors path="name" cssStyle="color:red"/>

    <br><br>

    Last Name:
    <form:input path="lastName"/>
    <form:errors path="lastName" cssStyle="color:red"/>

    <br><br>

    Age:
    <form:input path="age"/>
    <form:errors path="age" cssStyle="color:red"/>

    <br><br>

    <input type="submit" value="Add User">

</form:form>
</body>
</html>