<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                            <petclinic:layout pageName="actividades">
                                <jsp:attribute name="customScript">
                                     <script>
                                $(function() {
                                    $("#fechaInicio").datepicker({
                                        dateFormat: 'yy/mm/dd'
                                    });
                                    $("#fechaFin").datepicker({
                                        dateFormat: 'yy/mm/dd'
                                    });
                                });
                                     </script>

                        </jsp:attribute>
                        <jsp:body>
                                <h2>
                                    Anade actividades a tu Evento
                                </h2>
                                <form:form modelAttribute="actividad" class="form-horizontal" id="add-actividad-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="Temática" name="tematicaActividad" />
                                        <petclinic:inputField label="Descripción" name="descripcionActividad" />
                                        <petclinic:inputField label="Fecha de Inicio" name="fechaInicio" />
                                        <petclinic:inputField label="Fecha de Fin" name="fechaFin" />
                                            <petclinic:selectField label="Lugar de realizacion " name="lugarRealizacion" size="6" names="${listaId}" ></petclinic:selectField>
                                       <p> Elige lugar</p>
                                        <select name="lugarRealizacionId" >
                                            <c:forEach items="${lugaresRealizacion}" var="lugarRealizacion">
                                                  <option value="${lugarRealizacion.id}"> <c:out value="${lugarRealizacion.nombre_recinto}"></c:out> </option>
                                                </c:forEach>
                                        </select>
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