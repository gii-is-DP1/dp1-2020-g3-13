<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
                    <%@ taglib prefix ="form" uri = "http://www.springframework.org/tags/form"%>  
                    <%@page pageEncoding="UTF-8"%>

                        <petclinic:layout pageName="sponsor">
                            <jsp:body>
                                <h2>
                                    <c:if test="${sponsor['new']}">Añadir </c:if>Sponsor
                                </h2>
                                <form:form modelAttribute="sponsor" class="form-horizontal">
                                    <div class="form-group has-feedback">
                                        <div class="form-group">
                                            <div class="col-sm-10">


                                            </div>
                                        </div>
                                        <petclinic:inputField label="Empresa: " name="empresa" />
                                        <petclinic:inputField label="Url del logo: " name="urlLogo" />
                                        <petclinic:inputField label="Url de la empresa: " name="urlWeb" />


                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">

                                            <button class="btn btn-default" type="submit">Guardar Sponsor</button>
                                        </div>


                                    </div>
                                </form:form>
                            </jsp:body>
                        </petclinic:layout>