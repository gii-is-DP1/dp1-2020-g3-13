<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                            <head>
                                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                            </head>
                            <petclinic:layout pageName="entrada/new">
                                <h2>
                                    <c:if test="${entrada['new']}">Detalles de la entrada</c:if>
                                </h2>
                                <form:form modelAttribute="entrada" class="form-horizontal" id="add-admin-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="DNI: " name="dni" />
                                        <petclinic:inputField label="Nombre asistente:" name="nombreAsistente" />
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:choose>
                                                <c:when test="${entrada['new']}">
                                                    <button class="btn btn-default" type="submit">Añadir entrada</button>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </form:form>
                            </petclinic:layout>