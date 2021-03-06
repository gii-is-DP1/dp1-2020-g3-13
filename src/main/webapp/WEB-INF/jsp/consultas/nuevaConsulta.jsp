<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                            <%@page pageEncoding="UTF-8"%>

                            <head>
                                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                            </head>
                            <petclinic:layout pageName="consulta/new">
                                <h2>
                                    <c:if test="${consulta['new']}">Haz una consulta </c:if>
                                </h2>
                                <form:form modelAttribute="consulta" class="form-horizontal" id="add-admin-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="Asunto" name="asunto" />
                                        <petclinic:inputField label="Descripción" name="descripcion" />
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:choose>
                                                <c:when test="${consulta['new']}">
                                                    <button class="btn btn-default" type="submit">A&ntilde;adir consulta</button>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </form:form>
                                <spring:url value="/eventos" var="eventosUrl">
                            </spring:url>
                            <a href="${fn:escapeXml(eventosUrl)}" class="btn btn-default"><c:out value="Volver"/></a>
                            </petclinic:layout>