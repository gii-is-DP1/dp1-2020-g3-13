<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                <%@ page contentType="text/html; charset=UTF-8" %>

                    <petclinic:layout pageName="cliente/myprofile/edit">
                        <jsp:body>
                            <h2>
                                <c:if test="${cliente['new']}">New </c:if> Cliente
                            </h2>
                            <form:form modelAttribute="cliente" class="form-horizontal" action="/usuarios/myprofile/edit">

                                <div class="form-group has-feedback">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Cliente</label>
                                        <div class="col-sm-10">


                                        </div>
                                    </div>
                                    <petclinic:inputField label="nombre" name="nombre" />
                                    <petclinic:inputField label="email" name="email" />
                                    <petclinic:inputField label="telefono" name="telefono" />
                                    <petclinic:inputField label="apellidos" name="apellidos" />
                                    <petclinic:inputField label="nombreUsuario" name="usuario.nombreUsuario" />

                                    
                                    

                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">

                                        <button class="btn btn-default" type="submit">Editar cliente</button>
                                    </div>

                                </div>
                            </form:form>
                                


                        </jsp:body>
                    </petclinic:layout>