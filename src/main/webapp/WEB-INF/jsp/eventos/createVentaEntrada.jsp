
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="ventaEntrada">
    <h2>Compra Entradas</h2>
    <form:form modelAttribute="ventaEntrada" class="form-horizontal" id="add-ventaEntrada-form">
    <div class="form-group has-feedback">
        <petclinic:inputField label="Titular Tarjeta" name="nombreTitular"/>
        <petclinic:inputField label="Numero Tajeta" name="numTarjeta"/>
        <petclinic:inputField label="Fecha Caducidad" name="fechaCaducidad"/>
        <petclinic:inputField label="CVV" name="cvv"/>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Comprar</button>
        </div>
    </div>
</form:form>
</petclinic:layout>