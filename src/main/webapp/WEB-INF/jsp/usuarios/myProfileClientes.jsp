<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                        <%@page contentType="text/html"%>
                        <%@page pageEncoding="UTF-8"%>

                            <petclinic:layout pageName="usuarios">
                            
                                <div class="centrar-datos">
                                    <h2 style="text-align: left; color:rgb(130, 28, 120); size:20;">Mi perfil</h2>
                                    <div class="listar-Datos">
                                        <ul>
                                            <li>Nombre:
                                                <c:out value="${cliente.nombre}" />
                                            </li>
                                            <li>Apellidos:
                                                <c:out value="${cliente.apellidos}" />
                                            </li>
                                            <li>Email:
                                                <c:out value="${cliente.email}" />
                                            </li>
                                            <li>Teléfono:
                                                <c:out value="${cliente.telefono}" />
                                            </li>
                                            <li>Nombre de usuario:
                                                <c:out value="${cliente.usuario.nombreUsuario}" />
                                            </li>
                                            <spring:url value="myprofile/edit" var="edicionUrl">

                                            </spring:url>
                                            <h4></h4>
                                            <a href="${fn:escapeXml(edicionUrl)}"class="btn btn-default">Editar perfil</a></button>
                                            <c:out value="" /> 
                                            </a>
                                            
                                                <spring:url value="myprofile/delete" var="clienteUrl">                                  
                                                </spring:url>
                                                <a href="${fn:escapeXml(clienteUrl)}" class="btn btn-default">Borrar mi perfil</a></button>
                                                <c:out value="" /> 
                                                </a>
                                                </button>
                                            
                                        </ul>
                                    </div>
                                </div>


                            </petclinic:layout>