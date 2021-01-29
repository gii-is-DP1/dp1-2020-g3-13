<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                            <petclinic:layout pageName="usuarios">
                            
                                <div class="centrar-datos">
                                    <h2>Mi perfil</h2>
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
                                            <li>Telefono:
                                                <c:out value="${cliente.telefono}" />
                                            </li>
                                            <li>Nombre Usuario:
                                                <c:out value="${cliente.usuario.nombreUsuario}" />
                                            </li>
                                            <spring:url value="myprofile/edit" var="edicionUrl">

                                            </spring:url>
                                            <a href="${fn:escapeXml(edicionUrl)}"class="btn btn-default">Editar perfil</a></button>
                                            <c:out value="" /> 
                                            </a>
                                            
                                                <spring:url value="myprofile/delete" var="clienteUrl">                                  
                                                </spring:url>
                                                <a href="${fn:escapeXml(clienteUrl)}" class="btn btn-default">Eliminar cliente</a></button>
                                                <c:out value="" /> 
                                                </a>
                                                </button>
                                            
                                        </ul>
                                    </div>
                                </div>


                            </petclinic:layout>