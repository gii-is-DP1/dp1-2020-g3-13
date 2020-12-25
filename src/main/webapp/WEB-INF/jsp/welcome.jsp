<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                <!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->
                <div class="hero">
                    <div class="contenido-header">
                        <div class="informacion-evento">
                            <nav class="redes-sociales">
                                <a href="#"><i class="fab fa-facebook"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                                <a href="#"><i class="fab fa-pinterest-p"></i></a>
                                <a href="#"><i class="fab fa-youtube"></i></a>
                                <a href="#"><i class="fab fa-instagram"></i></a>
                            </nav>
                            <h1 class="nombre-sitio">QPlan</h1>
                        </div>
                    </div>
                </div>

                <petclinic:layout pageName="home">



                    <h2>
                        <fmt:message key="welcome" />
                    </h2>
                    <div class="row">
                        <h2>Project ${title}</h2>
                        <p>
                            <h2>Group ${group}</h2>
                        </p>
                        <p>
                            <ul>
                                <c:forEach items="${persons}" var="person">
                                    <li>${person.firstName} ${person.lastName}</li>
                                </c:forEach>
                            </ul>
                        </p>
                    </div>
                    <div class="row"></div>
                    <div class="col-md-12">
                        <!-- <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}"/>-->
                    </div>
                    </div>


                </petclinic:layout>