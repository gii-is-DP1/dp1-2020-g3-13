<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="tipoentradas">
    <h2>Entradas</h2>

    <table id="tipoentradasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Tipo de entrada</th>
            <th style="width: 200px;">Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tipoentradas}" var="tipoentrada">
            <tr>
                <td>
                    <c:out value="${tipoentrada.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${tipoentrada.precio}"/></a>
                </td>

                
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>