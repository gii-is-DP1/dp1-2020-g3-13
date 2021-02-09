<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="lugaresRealizacion">
    <h2>
        <c:if test="${lugarRealizacion['new']}">Nuevo </c:if> Lugar de Realizacion
    </h2>
    <form:form modelAttribute="lugarRealizacion" class="form-horizontal" id="add-lugarRealizacion-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre Recinto" name="nombre_recinto"/>
            <petclinic:inputField label="Aforo" name="aforo"/>
            <petclinic:inputField label="Direccion" name="direccion"/>
            <petclinic:inputField label="Caracteristicas" name="caracteristicas"/>
            <petclinic:inputField label="Telefono" name="telefono"/>
            <petclinic:inputField label="email" name="email"/>
            <petclinic:inputField label="Precio/dia" name="precio"/>
            <petclinic:inputField label="Foto" name="urlFoto"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${lugarRealizacion['new']}">
                        <button class="btn btn-default" type="submit">Añadir Lugar Realizacion</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar Lugar Realizacion</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
