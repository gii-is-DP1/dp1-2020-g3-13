<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                            <%@ taglib prefix ="form" uri = "http://www.springframework.org/tags/form"%>  
                                <%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="entrada/new">
                                <h2>
                                    Detalles de la entrada
                                </h2>
                                <form:form modelAttribute="entrada" class="form-horizontal" id="add-admin-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="DNI: " name="dni" />
                                        <petclinic:inputField label="Nombre asistente:" name="nombreAsistente" />
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <button class="btn btn-default" type="submit">Añadir entrada</button>
                                        </div>
                                    </div>
                                </form:form>

                                <spring:url value="/eventos/{eventoId}" var="eventosUrl">
                                <spring:param name="eventoId" value="${evento.id}" />
                        </spring:url>
                        <a href="${fn:escapeXml(eventosUrl)}" class="btn btn-default"><c:out value="Volver"/></a>
 </petclinic:layout>