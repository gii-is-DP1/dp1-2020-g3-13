<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                    <petclinic:layout pageName="organizaciones/myprofile/edit">
                        <jsp:body>
                            <h2>
                                <c:if test="${organizacion['new']}">New </c:if> Organizacion
                            </h2>
                            <form:form modelAttribute="organizacion" class="form-horizontal" action="/usuarios/myprofile/edit">

                                <div class="form-group has-feedback">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Organizacion</label>
                                        <div class="col-sm-10">


                                        </div>
                                    </div>
                                    <petclinic:inputField label="nombre" name="nombreOrganizacion" />
                                    <petclinic:inputField label="email" name="email" />
                                    <petclinic:inputField label="cif" name="cif" />
                                    <petclinic:inputField label="info" name="info" />
                                    <petclinic:inputField label="usuario" name="usuario.nombreUsuario" />
                                    <petclinic:inputField label="contraseña" name="usuario.password" />


                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">

                                        <button class="btn btn-default" type="submit">Editar organizacion</button>
                                    </div>

                                </div>
                            </form:form>
                                


                        </jsp:body>
                    </petclinic:layout>