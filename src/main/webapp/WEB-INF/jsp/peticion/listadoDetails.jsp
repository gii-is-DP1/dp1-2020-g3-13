<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                    <petclinic:layout pageName="peticion">

                        <h2>peticion Information</h2>


                        <table class="table table-striped">
                            <tr>
                                <th>Name</th>


                                <td>
                                    <spring:url value="/peticion/delete/{peticionid}" var="peticionUrl">
                                        <spring:param name="peticionid" value="${peticion.id}" />
                                    </spring:url>
                                    <a href="${fn:escapeXml(peticionUrl)}">
                                        <c:out value="${peticion.id}" /> ${peticion.id}
                                    </a>
                                </td>
                            </tr>

                        </table>


                        <br/>
                        <br/>
                        <br/>
                        <h2>Pets and Visits</h2>



                    </petclinic:layout>