<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                    <%@page pageEncoding="UTF-8"%>
                
                <petclinic:layout pageName="Eventos">

                                  
                
                    <h1 style="text-align: center; color:rgb(194, 19, 6)" >No puede comprar entrada de este evento porque ya ha finalizado</h1>
                    <h3 style="text-align: center;">
                    <a href="/eventos" title="Pagina de eventos"  > Volver al listado de eventos
                    
                    </a>
                    </h3>
                </petclinic:layout>

                
                
                <a href="/eventos" title="Pagina de eventos" class=""> </a>