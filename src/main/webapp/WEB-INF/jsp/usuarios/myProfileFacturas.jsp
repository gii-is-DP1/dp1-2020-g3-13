<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                            <petclinic:layout pageName="facturas">
                                <div class="centrar-datos">
                                    <h2>Mis Facturas</h2>
                                    <div class="listar-Datos">
                                        <c:forEach items="${usuario.facturas}" var="factura">
                                        <ul>
                                            <li>Precio Total:
                                                <c:out value="${factura.precioTotal}" />
                                            </li>
                                            <li>Fecha de la factura:
                                                <c:out value="${factura.fechaFactura}" />
                                            </li>
                                            <li>Líneas de la factura:
                                                <c:forEach items="${factura.lineasFactura}" var="lineas">
                                                    <c:out value="${lineas}"/>
                                                </c:forEach>
                                            </li>
                                        </ul>
                                    </c:forEach>
                                    </div>
                                </div>


                            </petclinic:layout>