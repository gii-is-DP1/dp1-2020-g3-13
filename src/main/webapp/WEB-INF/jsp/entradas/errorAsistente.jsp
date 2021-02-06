<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@page pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
              
                
                <petclinic:layout pageName="Entradas">

                                  
                
                    <h1 style="text-align: center; color:rgb(194, 19, 6)" >No puede comprar más de una entrada para el mismo asistente</h1>
                    <h3 style="text-align: center;">
                    <a href="javascript:history.back()"> Volver a la vista de compras</a>
                    
                    </a>
                    </h3>
                </petclinic:layout>

                
                
