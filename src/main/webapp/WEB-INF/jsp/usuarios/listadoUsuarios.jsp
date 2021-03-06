<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %> 
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="usuarios">
    <h2>Usuarios</h2>

    <table id="usuariosTable" class="table table-striped">
        <p>Número de usuarios: <c:out value="${usuarios.size()} "/></p>
        <thead>
        
        <tr>
            <th style="width: 150px;">Nombre de usuario</th>
            <th style="width: 200px;">Autoridades</th>
            <th style="width: 200px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usuarios}" var="usuarios">
            <tr>

                <td>
                    <spring:url value="/usuarios/{usuarioId}" var="usuarioUrl">
                    <spring:param name="usuarioId" value="${usuarios.nombreUsuario}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(usuarioUrl)}"><c:out value="${usuarios.nombreUsuario} "/></a>
                </td>

                <td>
                    <c:out value="${usuarios.autoridades.autoridad} "/>

                </td>
                
                    <div class="borrar">
                        <c:choose>
                        <c:when test="${usuarios.autoridades.autoridad=='admin'}">
                            <td></td>
                        </c:when>
                        <c:otherwise >
                            <td><spring:url value="/usuarios/{usuarioId}/delete" var="deleteUrl">
                                <spring:param name="usuarioId" value="${usuarios.nombreUsuario}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default" onclick="return confirm('¿Esta seguro de que quiere borrar el usuario?')">Borrar Usuario</a>
                        </c:otherwise>
                        </c:choose>
                    </div>
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
