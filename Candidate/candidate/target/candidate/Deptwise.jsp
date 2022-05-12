<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
Dept:   

<!-- <input type="text" name="dept"> </input> -->
<form action="deptdept" method="post">
<select name="dept">
    <option value="dept_id">HR</option>
    <option value="dept_id">ADMIN</option>
    <option value="dept_id">SALES</option>
    <option value="dept_id">MANAGING</option>
</select>

<input type="submit" value="Submit" > </input>
</body>
</html>