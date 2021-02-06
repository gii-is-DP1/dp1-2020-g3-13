<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                        <%@page pageEncoding="UTF-8"%>
                            
                            <style type="text/css">
                            h2 { color:#ff7300;font-weight:bold;font-family:Calibri;font-size:20 }
                            </style>
                            <petclinic:layout pageName="facturas">
                                <div class="centrar-datos">
                                    <div class="listar-Datos">
                                        <h2>Facturas de la organizacion <c:out value="${org.nombreOrganizacion}" /></h2>
                                        <c:forEach items="${usuario.facturas}" var="factura">
                                        <ul>
                                            <li>Precio Total:
                                                <c:out value="${factura.precioTotal}" />
                                            </li>
                                            <li>Fecha de la factura:
                                                <c:out value="${factura.fechaFactura}" />
                                            </li>
                                            <li>Lineas de la factura:<br>
                                                <c:forEach items="${factura.lineasFacturas}" var="lineas">
                                                <p>-Lugar Alquilado: <c:out value="${lineas.alquilerEspacio.lugarRealizacion.nombre_recinto}"/></p>
                                                <p>-Dias: <c:out value="${lineas.cantidad}"/></p>
                                                <p>-Precio: <c:out value="${lineas.alquilerEspacio.precioTotal}"/></p>                                 
                                                </c:forEach>
                                            </li>
                                        </ul>
                                    </c:forEach>
                                    </div>
                                </div>


                            </petclinic:layout>