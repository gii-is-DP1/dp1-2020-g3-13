<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                        <%@page pageEncoding="UTF-8"%>

                        <petclinic:layout pageName="eventos">
                            <section class="seccion contenedor">
                                <h2>Calendario de Eventos</h2>

                                <div class="calendario">
                                    <h3>
                                        <i class="fa fa-calendar" aria-hidden="true"></i> Eventos proximos </h3>
                                </div>
                                <tbody>
                                    <c:forEach items="${eventos}" var="evento">
                                        <div class="evento">
                                            <c:choose>
                                            <c:when test="${evento.esPublico}">
                                                <spring:url value="/eventos/{eventosId}" var="eventoUrl">
                                                <spring:param name="eventosId" value="${evento.id}" />
                                                </spring:url>
                                                <a href="${fn:escapeXml(eventoUrl)}">
                                                <p class="tituloEvento">
                                                <c:out value="${evento.nombreEvento}" />
                                                </p>
                                            </a>

                                            <p class="tipoEvento"><i class="fas fa-theater-masks"></i>
                                                <c:out value="${evento.tipoEvento}" />
                                            </p>
                                            <p class="fechaEvento"> <i class="far fa-calendar-alt"></i>
                                                <c:out value="${evento.fechaInicio}" />
                                            </p>
                                            <p cass="descripcionEvento">
                                                <c:out value="${evento.descripcion}" />
                                            </p>
                                            <spring:url value="/eventos/{eventosId}" var="eventoUrl">
                                                <spring:param name="eventosId" value="${evento.id}" />
                                            </spring:url>
                                            <a href="${fn:escapeXml(eventoUrl)}">
                                                <p class="enlaceEvento"> <i class="fa fa-calendar"></i> Detalle
                                                </p>
                                            </a>
                                            <spring:url value="/consultas/{eventoId}/nuevo" var="nuevaConsultaUrl">
                                            <spring:param name="eventoId" value="${evento.id}" />
                                            </spring:url>
                                            <a href="${fn:escapeXml(nuevaConsultaUrl)}">
                                            <p class="enlaceEvento"> <i class="fas fa-ticket-alt"></i> Consulta
                                            </p>
                                            </a>
                                            </c:when>
                                            </c:choose>
                                        </div>
                                    </c:forEach>
                                </tbody>
                            </section>
                        </petclinic:layout>