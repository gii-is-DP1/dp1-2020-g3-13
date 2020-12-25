<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                    <!--https://stackoverflow.com/questions/15480397/how-to-send-list-of-objects-to-view-and-back-to-post-method-in-controller-->
                    <petclinic:layout pageName="crearExponentes">
                        <h2>A&ntilde;adir exponente</h2>
                        <form:form modelAttribute="exponentes" class="form-horizontal" id="add-ventaEntrada-form">
                            <div class="form-group has-feedback">
                                <petclinic:inputField label="Titular Tarjeta" name="exponentes" />
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <!--      <button class="btn btn-default" type="submit">Comprar</button> -->
                                </div>
                            </div>
                        </form:form>
                    </petclinic:layout>