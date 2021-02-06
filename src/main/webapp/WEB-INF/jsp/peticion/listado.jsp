<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %> 
<%@ page contentType="text/html; charset=UTF-8" %>



                        <petclinic:layout pageName="peticion/listado">
                            <h2>Peticiones</h2>

                            <table id="peticionesTable" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th style="width: 150px;">Nombre organización</th>
                                        <th style="width: 200px;">Información</th>
                                        <th style="width: 200px;">Email</th>
                                        <th style="width: 200px;">Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${peticion}" var="peticion">
                                        <tr>
                                            <td>

                                                <c:out value="${peticion.nombre_organizacion}" />
                                                </a>
                                            </td>
                                            <td>
                                                <c:out value="${peticion.info}" />
                                                </a>
                                            </td>
                                            <td>
                                                <c:out value="${peticion.email}" />
                                            </td>
                                            <td>
                                                <spring:url value="/peticion/{peticionid}" var="peticionUrl">
                                                    <spring:param name="peticionid" value="${peticion.id}" />
                                                </spring:url>
                                                <a href="${fn:escapeXml(peticionUrl)}">
                                                    <img class="img-responsive" src="/resources/images/icons/details.png" width="10%" />
                                                    <c:out value="${peticion.id}" />
                                                </a>
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