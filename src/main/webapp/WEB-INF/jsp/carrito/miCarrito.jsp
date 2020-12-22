<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                    <petclinic:layout pageName="carrito">

                        <h2>Detalles del carrito ;)
                        </h2>

                        <h3>¿Qué llevo en mi carrito?</h3>
                        <p> Total: </p>
                        <c:out value="${carrito.total}" />


                        <c:forEach items="${carrito.lineasFacturas}" var="lineasFacturas">
                            <!--   <c:forEach items="${lineasFactura.tipoEntrada}" var="tipoEntrada">-->
                            <p>
                                Nombre entrada:
                                <c:out value="${carrito.lineasFacturas.cantidad}" />
                            </p>
                            <!--  <p>Precio:
                                    <c:out value="${tipoEntradas.precio} " />EUR </p>
                                </div>
                                <p>Fecha inicio:
                                    <c:out value="${tipoEntradas.fechaInicio}" />
                                </p>
                                <p>Fecha Fin:
                                    <c:out value="${tipoEntradas.fechaFin}" />
                                </p>
                                <spring:url value="/eventos/{eventoId}/{tipoEntradaId}/entrada" var="ventaUrl">
                                    <spring:param name="eventoId" value="${evento.id}" />
                                    <spring:param name="tipoEntradaId" value="${tipoEntradas.id}" />
                                </spring:url>
                                <a href="${fn:escapeXml(ventaUrl)}">
                                    <c:out value="Comprar" /><br></a>
                                -->
                            <!--    </c:forEach>-->
                        </c:forEach>



                    </petclinic:layout>