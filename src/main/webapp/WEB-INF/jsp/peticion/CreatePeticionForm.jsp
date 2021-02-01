<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                        <%@page contentType="text/html"%>
                        <%@page pageEncoding="UTF-8"%>

                            <head>
                                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                            </head>
                            <petclinic:layout pageName="peticion/new">
                                <h2 style="text-align: center; color:rgb(95, 20, 72); size:20">
                                    <c:if test="${peticion['new']}">Registrarse como Organización </c:if>
                                </h2>
                                <form:form modelAttribute="peticion" class="form-horizontal" id="add-admin-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="Email" name="email" />
                                        <petclinic:inputField label="Nombre de organizacion" name="nombre_organizacion" />
                                        <petclinic:inputField label="C.I.F." name="cif" />
                                        <petclinic:inputField label="Información" name="info" />
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:choose>
                                                <c:when test="${peticion['new']}">
                                                    <button class="btn btn-default" type="submit">Añadir petición</button>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </form:form>
                            </petclinic:layout>