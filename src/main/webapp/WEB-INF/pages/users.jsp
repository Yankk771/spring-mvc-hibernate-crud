<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<form action="/add" method="post">

    Name:
    <input type="text" name="name">

    Last Name:
    <input type="text" name="lastName">

    Age:
    <input type="number" name="age">

    <input type="submit" value="Add User">

</form>

</body>
</html>