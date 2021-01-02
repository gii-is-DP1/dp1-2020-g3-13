<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                            <petclinic:layout pageName="clientes">
                                <h2>
                                    <c:if test="${cliente['new']}">Registro de nuevo </c:if> Cliente
                                </h2>
                                <form:form modelAttribute="cliente" class="form-horizontal" id="add-cliente-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="Nombre de usuario" name="usuario.nombreUsuario" />
                                        <petclinic:passwordField label="Contraseña" name="usuario.password" />
                                        <petclinic:inputField label="Nombre" name="nombre" />
                                        <petclinic:inputField label="Apellidos" name="apellidos" />
                                        <petclinic:inputField label="Email" name="email" />
                                        <petclinic:inputField label="Teléfono" name="telefono" />
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:choose>
                                                <c:when test="${cliente['new']}">
                                                    <button class="btn btn-default" type="submit">Registrarse como cliente</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="btn btn-default" type="submit">Editar cliente</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </form:form>
                            </petclinic:layout>