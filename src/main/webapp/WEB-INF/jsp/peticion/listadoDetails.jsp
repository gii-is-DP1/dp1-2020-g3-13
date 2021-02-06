<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                    <%@page pageEncoding="UTF-8"%>

                    <petclinic:layout pageName="peticion">

                        <h2>peticion Information</h2>

                        <ul>
                            <li>
                                <label>Nombre organizacion:</label>
                                <c:out value="${peticion.nombre_organizacion}" />
                            </li>
                            <li>
                                <label>CIF:</label>
                                <c:out value="${peticion.cif}" />
                            </li>
                            <li>
                                <label>Info:</label>
                                <c:out value="${peticion.info}" />
                            </li>
                            <li>
                                <label>Email:</label>
                                <c:out value="${peticion.email}" />
                            </li>
                            <li>
                                <label>Fecha:</label>
                                <c:out value="${peticion.fecha}" />
                            </li>
                            <li>
                                <label>Opciones:</label>

                            </li>
                            <li class="button">
                                <spring:url value="/peticion/delete/{peticionid}" var="peticionUrl">
                                    <spring:param name="peticionid" value="${peticion.id}" />
                                </spring:url>
                                <a href="${fn:escapeXml(peticionUrl)}" class="btn btn-default" onclick="return confirm('¿Eliminar esta petición?\nCIF: ${peticion.cif}')">Eliminar organizacion</a></button>
                                <c:out value="" /> Rechazar
                                </a>
                                </button>
                            </li>
                            <li class="button">
                                <spring:url value="/peticion/{peticionid}/create" var="peticionUrl">
                                    <spring:param name="peticionid" value="${peticion.id}" />
                                </spring:url>
                                <a href="${fn:escapeXml(peticionUrl)}" class="btn btn-default" onclick="return confirm('Â¿Crear esta nueva organizaciÃ³n?\nCIF: ${peticion.cif}')">Crear organizacion</a></button>
                                <c:out value="" /> Aceptar
                                </a>
                                </button>
                            </li>

                        </ul>
                        <table class="table table-striped">




                            <br/>
                            <br/>
                            <br/>




                    </petclinic:layout>