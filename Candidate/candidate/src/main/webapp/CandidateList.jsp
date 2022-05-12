<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Candidates Application</title>
</head>
<body>
    <!-- <a href="/Upload.jsp">Upload</a> -->
    <center>
        <h1>Candidates Management</h1>
        <h2>
            <a href="/new">Add New Candidate</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Candidates</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/dept">department wise</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Candidates</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Dept</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="candidate" items="${listCandidate}">
                <tr>
                    <td><c:out value="${candidate.id}" /></td>
                    <td><c:out value="${candidate.name}" /></td>
                    <td><c:out value="${candidate.email}" /></td>
                    <td><c:out value="${candidate.dept_id}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${candidate.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${candidate.id}' />">Delete</a> 
                                            
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>
