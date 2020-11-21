<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                            <petclinic:layout pageName="peticion/new">
                                <h2>
                                    <c:if test="${peticion['new']}">New </c:if> Peticion
                                </h2>
                                <form:form modelAttribute="peticion" class="form-horizontal" id="add-admin-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="Email" name="Email" />
                                        <petclinic:inputField label="nombre_organizacion" name="nombre_organizacion" />
                                        <petclinic:inputField label="cif" name="cif" />
                                        <petclinic:inputField label="info" name="info" />
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:choose>
                                                <c:when test="${peticion['new']}">
                                                    <button class="btn btn-default" type="submit">Add Peticion</button>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </form:form>
                            </petclinic:layout>