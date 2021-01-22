<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="organizaciones">
    <h2>
        <c:if test="${organizacion['new']}">Registro de nueva </c:if> Organizacion
    </h2>
    <form:form modelAttribute="organizacion" class="form-horizontal" id="add-organizacion-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre de usuario" name="usuario.nombreUsuario"/>
            <petclinic:passwordField label="Contraseña" name="usuario.password"/>
            <petclinic:inputField label="Nombre" name="nombreOrganizacion"/>
            <petclinic:inputField label="Cif" name="cif"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Info" name="info"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${organizacion['new']}">
                        <button class="btn btn-default" type="submit">Registrarse como organizacion</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Editar organizacion</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
