<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Candidates Application</title>
</head>
<body>
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
        <c:if test="${candidate != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${candidate == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${candidate != null}">
                        Edit Candidate
                    </c:if>
                    <c:if test="${candidate == null}">
                        Add New Candidate
                    </c:if>
                </h2>
            </caption>
                <c:if test="${candidate != null}">
                    <input type="hidden" name="id" value="<c:out value='${candidate.id}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${candidate.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="45"
                            value="<c:out value='${candidate.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Dept: </th>
                <td>
            
                    <select name="dept">
                        <option value="10">HR</option>
                        <option value="20">SALES</option>
                        <option value="30">ADMIN</option>
                        <option value="40">MANAGING</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>