<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                    <petclinic:layout pageName="test">
                        <jsp:body>
                            <h2>
                                <c:if test="${exponente['new']}">A&ntilde;adir </c:if> exponente
                            </h2>
                            <form:form modelAttribute="exponente" class="form-horizontal">
                                <div class="form-group has-feedback">
                                    <div class="form-group">
                                        <div class="col-sm-10">


                                        </div>
                                    </div>
                                    <petclinic:inputField label="Nombre del exponente: " name="nombreExponente" />
                                    <petclinic:inputField label="Apellidos del exponente: " name="apellidosExponente" />
                                    <petclinic:inputField label="Alias del exponente: " name="alias" />


                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">

                                        <button class="btn btn-default" type="submit">Editar cliente</button>
                                    </div>

                                </div>
                            </form:form>

                            <!--Terminar !!!! -->
                            <table id="exponentesTabla" class="table table-striped">
                                <tr>
                                    <th style="width: 150px;">Nombre exponente</th>
                                    <th style="width: 200px;">Apellidos</th>
                                    <th style="width: 200px;">Alias</th>
                                    <th style="width: 200px;">Opciones</th>
                                </tr>
                                <c:forEach items="${listaExponentes}" var="exponente">


                                    <tr>
                                        <td>

                                            <c:out value="${exponente.nombreExponente}" />

                                        </td>
                                        <td>

                                            <c:out value="${exponente.apellidosExponente}" />

                                        </td>
                                        <td>

                                            <c:out value="${exponente.alias}" />

                                        </td>

                                    </tr>



                                </c:forEach>
                            </table>



                        </jsp:body>
                    </petclinic:layout>