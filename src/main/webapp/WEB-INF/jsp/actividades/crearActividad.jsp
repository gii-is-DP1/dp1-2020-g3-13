<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                            <%@page pageEncoding="UTF-8"%>
                            <petclinic:layout pageName="actividades">
                        <jsp:body>
                                <h2>
                                    Añade actividades a tu Evento
                                </h2>
                                <form:form modelAttribute="actividad" class="form-horizontal" id="add-actividad-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="Temática" name="tematicaActividad"/>
                                        <petclinic:inputField label="Descripción" name="descripcionActividad" />
                                        <petclinic:inputField id="datetimeInicio" label="Fecha de Inicio" name="fechaInicio" />
                                        <petclinic:inputField id="datetimeFin" label="Fecha de Fin" name="fechaFin" />
                                        

                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:choose>
                                                <c:when test="${actividad['new']}">
                                                    <button class="btn btn-default" type="submit">Anadir Actividad</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="btn btn-default" type="submit">Editar Actividad</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </form:form>
                            </jsp:body>

                            </petclinic:layout>