<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Eventos">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaInicio").datepicker({dateFormat: 'yy/mm/dd'});
                $("#fechaFin").datepicker({dateFormat: 'yy/mm/dd'});
            });
         
        </script>
    </jsp:attribute>
    <jsp:body>
     
        <h2>
            Evento
        </h2>

        <form:form modelAttribute="evento" class="form-horizontal" id="add-evento-form">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Nombre" name="nombreEvento"/>
                <petclinic:inputField label="Tipo de evento" name="tipoEvento"/>
                <petclinic:inputField label="Descripcion" name="descripcion"/>
                <petclinic:inputField label="Fecha de inicio" name="fechaInicio"/>
                <petclinic:inputField label="Fecha de fin" name="fechaFin"/>
                <petclinic:inputField label="Categoria" name="categoria"/>
                <petclinic:inputField label="Medidas Sanitarias" name="medidasSanitarias"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">

                        <button class="btn btn-default" type="submit">Guardar evento</button>
 
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>
