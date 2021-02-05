<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                <!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                        <%@page contentType="text/html"%>
                        <%@page pageEncoding="UTF-8"%>  
                            <div class="hero">
                                <div class="contenido-header">
                                    <div class="informacion-evento">
                                        <nav class="redes-sociales">
                                            <a href="#"><i class="fab fa-facebook"></i></a>
                                            <a href="#"><i class="fab fa-twitter"></i></a>
                                            <a href="#"><i class="fab fa-pinterest-p"></i></a>
                                            <a href="#"><i class="fab fa-youtube"></i></a>
                                            <a href="#"><i class="fab fa-instagram"></i></a>
                                        </nav>
                                        <h1 class="nombre-sitio">QPlan</h1>
                                    </div>
                                </div>
                            </div>

                            <petclinic:layout pageName="home">
                                <div class="calendario">
                                    <h3>
                                        <i class="fa fa-calendar" aria-hidden="true"></i> Nuestros próximos eventos </h3>
                                </div>
                                <!-- <section class="contenido-descriptivo">
                                    <div class="contenido-video">
                                        <video muted autoplay loop poster="/resources/images/captura_video.PNG">
                                        <source src="/resources/images/videos/video_entrada_mp4.mp4" type="video/mp4">
                                        <source src="/resources/images/videos/video_entrada_mp4.webm" type="video/webm">
                                        <source src="/resources/images/videos/video_entrada_mp4.ogv" type="video/ogv">
                                        </video>
                                    </div>
                                <div class="contenido-programa">-->
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

                                    </div>
                                </c:forEach>
                                <!--  </div>

                                </section>-->






                            </petclinic:layout>