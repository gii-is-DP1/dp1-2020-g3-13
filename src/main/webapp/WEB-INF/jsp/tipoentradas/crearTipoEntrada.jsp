<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                            <%@page pageEncoding="UTF-8"%>







                            <petclinic:layout pageName="tipoEntrada">
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
                        <jsp:body>
                                <h2>
                                    Añade los Tipos de Entradas a tu Evento
                                </h2>
                                <form:form modelAttribute="tipoEntrada" class="form-horizontal" id="add-tipoEntrada-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:selectFieldTipoEntrada label="Selecciona el tipo de Entrada" name="nombre" names="${NombreTipoEntrada}" size ="1"/>
                                        <petclinic:inputField label="Numero de entradas disponibles" name="numEntradas" />
                                        <petclinic:inputField label="Fecha de Inicio" name="fechaInicio" id="fechaInicio" />
                                        <petclinic:inputField label="Fecha de Fin" name="fechaFin" id="fechaFin" />
                                        <petclinic:inputField label="Precio" name="precio" />

                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:choose>
                                                <c:when test="${tipoEntrada['new']}">
                                                    <button class="btn btn-default" type="submit">Anadir Tipo Entrada</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="btn btn-default" type="submit">Editar Tipo Entrada</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </form:form>
                            </jsp:body>
                            </petclinic:layout>