<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- Placed at the end of the document so the pages load faster --%>
<spring:url value="/webjars/jquery/2.2.4/jquery.min.js" var="jQuery"/>
<script src="${jQuery}"></script>

<%-- jquery-ui.js file is really big so we only load what we need instead of loading everything --%>
<spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.js" var="jQueryUiCore"/>
<script src="${jQueryUiCore}"></script>

<%-- Bootstrap --%>
<spring:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"></script>

<spring:url value="/resources/js/jquery.lettering.js" var="jqueryLettering"/>
<script type="text/javascript" src="${jqueryLettering}"></script>
<spring:url value="/resources/js/funcionesJavascript.js" var="javascriptPrincipal"/>
<script type="text/javascript" src="${javascriptPrincipal}"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>