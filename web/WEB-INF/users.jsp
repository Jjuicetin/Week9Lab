<%-- 
    Document   : users
    Created on : 24-Oct-2022, 4:19:16 PM
    Author     : puppi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Something</title>
    </head>
    <body>
        <h1>Manage Users</h1>
           
             <c:choose>
            <c:when test="${users.size() == 0}">
                <h4>No users found. Please add a user.</h4>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <!-- two blank td is for the edit and delete -->
                        <td></td>
                        <td></td>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role.roleName}</td>
                            <td>
                                <a href="<c:url value='/users'><c:param name='email' value='${user.email}'/><c:param name='action' value='edit'/></c:url>">Edit</a>
                            </td>
                            <td>
                                <a href=" <c:url value='/users'><c:param name='email' value='${user.email}'/><c:param name='action' value='delete'/></c:url>">Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
                
         <form action="Users" method="post">     
        </c:choose>       
        <c:when test="${!isEdit}">
             Email: <input type="text" name="email" required> <br>
             First name: <input type="text" name="fname" required> <br>
             Last name: <input type="text" name="lname" required> <br>
             Password: <input type="password" name="password" required> <br>
             Role: <select name="roles" required>
                 <option value="sysadmin">system admin</option>
                 <option value="regular">regular user</option>
             </select>
             <br>
             <input type="hidden" name="action" value="add">
             <input type="submit" value="Add user">
             </c:when>
            
             <c:otherwise>
                 <input type="hidden" name="email" value="${user.email}"> 
                    Email: ${user.email} <br>
                    First name: <input type="text" name="fname" value="${user.firstName}"> <br>
                    Last name: <input type="text" name="lname" value="${user.lastName}"> <br>
                    Password: <input type="password" name="password"> <br>
                     <input type="submit" value="Update" name="action">
                     <input type="submit" value="Cancel" name="action">
             </c:otherwise>
                
        <h2>Add Users</h2>
        <h4> add user is replaced with edit user when edit link is pressed </h4>
         
           
            
         </form>
    </body>
</html>
