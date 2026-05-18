<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Edit User</title>
</head>
<body>

<h2>Edit User</h2>

<form action="/update" method="post">

  <input type="hidden"
         name="id"
         value="${user.id}">

  Name:
  <input type="text"
         name="name"
         value="${user.name}">

  Last Name:
  <input type="text"
         name="lastName"
         value="${user.lastName}">

  Age:
  <input type="number"
         name="age"
         value="${user.age}">

  <input type="submit"
         value="Update">

</form>

</body>
</html>