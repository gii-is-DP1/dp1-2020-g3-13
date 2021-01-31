<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                    <%@page contentType="text/html"%>
                        <%@page pageEncoding="UTF-8"%>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
                        <petclinic:layout pageName="test">
                            <jsp:body>

                                <h1 style="text-align: center; color:rgb(194, 19, 6); size:20;">Usted no tiene permiso para acceder a este evento.</h1>
                                <a href="/eventos" title="Pagina de eventos"  > Volver al listado de eventos </a>

                            </jsp:body>
                        </petclinic:layout>