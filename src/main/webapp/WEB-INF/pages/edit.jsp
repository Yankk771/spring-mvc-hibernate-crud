<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Edit User</title>
</head>
<body>

<h2>Edit User</h2>

<form:form action="/update" method="post" modelAttribute="user">

<form:input type="hidden" path="id"/>

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

<input type="submit" value="Update">

</form:form>

</body>
</html>