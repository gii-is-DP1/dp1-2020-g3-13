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
                            <petclinic:layout pageName="consulta/respuesta">
                                <h2>
                                    Formulario de respuesta a la consulta
                                </h2>
                                <c:out value="${consulta.fechaConsulta}"/>
                                <c:out value="${consulta.evento.nombreEvento}"/>
                                <c:out value="${consulta.asunto}"/>
                                <c:out value="${consulta.descripcion}"/>
                                <form:form modelAttribute="consulta" class="form-horizontal" id="add-admin-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="Respuesta" name="respuesta" />
                                    </div>
                                    <petclinic:inputFieldHidden label="Descripcion" name="descripcion"  value="${consulta.descripcion}"/>
                                    <petclinic:inputFieldHidden label="Descripcion" name="asunto"  value="${consulta.asunto}"/>

                                    
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <button class="btn btn-default" type="submit">a&ntilde;adir Consulta</button>
                                        </div>
                                    </div>
                                </form:form>
                            </petclinic:layout>