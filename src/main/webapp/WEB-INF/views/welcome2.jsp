<%--
  Created by IntelliJ IDEA.
  User: kamel
  Date: 1/12/2017
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Search by city:
<form action="searchByCity" method="get">
    <input type="text" name="city">
    <input type="submit" value="Search">

</form>
<br>

Search by country:
<form action="searchByCountry" method="get">
    <select name="country" onchange = "this.form.submit()">
        <option value="Select Country">Select Country</option>
        <option value="France">France</option>
        <option value="Germany">Germany</option>
        <option value="Mexico">Mexico</option>
        <option value="Denmark">Denmark</option>
        <option value="Austria">Austria</option>

    </select>
    <%--<input type="submit" value="Search">--%>
</form>
<br>

<table border=1>
    <c:forEach var="myvar" items="${cList}">
        <tr>
            <td> ${myvar.customerId}</td>
            <td> ${myvar.companyName}</td>

            <td><a href="delete?id=${myvar.customerId}" onclick = "return confirm('Are you sure?')"> Delete </a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
