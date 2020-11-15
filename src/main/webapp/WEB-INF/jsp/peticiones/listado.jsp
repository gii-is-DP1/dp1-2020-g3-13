<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="peticion">
    <h2>Admins</h2>

    <table id="peticionesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">nombreOrganizacion</th>
            <th style="width: 200px;">info</th>
            <th style="width: 200px;">email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${peticion}" var="peticion">
            <tr>
                <td>

                    <c:out value="${peticion.nombreOrganizacion}"/></a>
                </td>
                <td>
                    <c:out value="${peticion.info}"/></a>
                </td>
                <td>
                    <c:out value="${peticion.email}"/>
                </td>
                
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
