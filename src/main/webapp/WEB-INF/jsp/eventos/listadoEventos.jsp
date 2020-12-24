<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

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


                                            <p class="tituloEvento">
                                                <c:out value="${evento.nombreEvento}" />
                                            </p>


                                            <p class="tipoEvento"><i class="fas fa-theater-masks"></i>
                                                <c:out value="${evento.tipoEvento}" />
                                            </p>
                                            <p class="categoriaEvento">
                                                <c:out value="${evento.categoria}" />
                                            </p>
                                            <p class="fechaEvento"> <i class="far fa-calendar-alt"></i>
                                                <c:out value="${evento.fechaInicio}" />
                                            </p>
                                            <p cass="descripcionEvento">
                                                <c:out value="${evento.descripcion}" />
                                            </p>
                                            <p class="medidasSanitarias"> <i class="fas fa-head-side-mask"></i>
                                                <c:out value="${evento.medidasSanitarias}" />
                                            </p>
                                            <spring:url value="/eventos/{eventosId}" var="eventoUrl">
                                                <spring:param name="eventosId" value="${evento.id}" />
                                            </spring:url>
                                            <a href="${fn:escapeXml(eventoUrl)}">
                                                <p class="enlaceEvento"> <i class="fas fa-plus"></i> A&ntilde;dir entrada
                                                </p>
                                            </a>
                                        </div>
                                    </c:forEach>
                                </tbody>



                            </section>








                            <!--Nombre evento

tipo de evento

Categoria

Fecha inicio

Descripcion

Medidas sanitarias
                            <div class="dia">
                                <p class="titulo">HTML5 y CSS3</p>
                                <p class="hora"><i class="fa fa-clock-o" aria-hidden="true"></i> 2016-12-09 02:00:00 hrs
                                    <p>

                                        <i class="fa fa-code" aria-hidden="true"></i> Taller </p>
                                    <p><i class="fa fa-user" aria-hidden="true"></i> Juan Sanchez </p>

                            </div>
-->
                        </petclinic:layout>