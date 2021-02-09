<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                        <%@page pageEncoding="UTF-8"%>

                            <petclinic:layout pageName="organizaciones ">
                                <div class="centrar-datos">
                                    <h2>Mi perfil</h2>
                                    <div class="listar-Datos">
                                        <ul>
                                            <li>Nombre:
                                                <c:out value="${organizacion.nombreOrganizacion}" />
                                            </li>
                                            <li>Cif:
                                                <c:out value="${organizacion.cif}" />
                                            </li>
                                            <li>Email:
                                                <c:out value="${organizacion.email}" />
                                            </li>
                                            <li>Info:
                                                <c:out value="${organizacion.info}" />
                                            </li>
                                            <li>Nombre Usuario:
                                                <c:out value="${organizacion.usuario.nombreUsuario}" />
                                            </li>
                                            <spring:url value="myprofile/edit" var="edicionUrl">

                                            </spring:url>
                                            <a href="${fn:escapeXml(edicionUrl)}"class="btn btn-default">Editar perfil</a></button>
                                            <c:out value="" /> 
                                            </a>
                                            
                                                <spring:url value="myprofile/delete" var="organizacionUrl">                                  
                                                </spring:url>
                                                <a href="${fn:escapeXml(organizacionUrl)}" class="btn btn-default">Eliminar organizacion</a></button>
                                                <c:out value="" /> 
                                                </a>
                                                </button>
                                            
                                            
                                            
                                        </ul>
                                    </div>
                                </div>


                            </petclinic:layout>