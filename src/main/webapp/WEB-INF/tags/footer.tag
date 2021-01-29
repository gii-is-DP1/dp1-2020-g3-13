<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8"%>

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
<spring:url value="/resources/js/moments.js" var="jqueryMoments"/>
<script type="text/javascript" src="${jqueryMoments}"></script>
<spring:url value="/resources/js/bootstrap-datetimepicker.min.js" var="jequeryMinBoostrap"/>
<script type="text/javascript" src="${jequeryMinBoostrap}"></script>
<spring:url value="/resources/js/bootsrap.min.js" var="jequeryMin"/>
<script type="text/javascript" src="${jequeryMin}"></script>

<spring:url value="/resources/js/funcionesJavascript.js" var="javascriptPrincipal"/>
<script type="text/javascript" src="${javascriptPrincipal}"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>

<footer>

    <div class="footer-container-all">
        <div class="footer-container-body">

            <div class="footer-column1">
                <h1>Sobre Nosotros</h1>
                <p>Somos una compañía joven dedicada a la promoción de eventos
                    en la ciudad de Sevilla. Organizamos los eventos, vendemos
                    las entradas para estos además de permitir el alquiler de 
                    espacios en todas las instalaciones con las que estamos
                    asociados. ¡Si te encanta visitar los eventos que se celebran
                    en tu ciudad no dudes en visitarnos. Si eres una organización
                    con ganas de celebrar tu show, no dudes en enviar una petición
                    y hacer tus primeros eventos en la ciudad!.
                </p>

            </div>
            <div class="footer-column2">

                <h1>Nuestras redes sociales</h1>

                <div class="row">
                    <a href="#"><img src="https://cdn.iconscout.com/icon/free/png-256/facebook-logo-2019-1597680-1350125.png" alt="">
                    <label>Siguenos en Facebook </label>
                </a>
                </div>
                <div class="row">
                    <a href="#"><img src="https://cdn.iconscout.com/icon/free/png-256/twitter-213-569318.png" alt="">
                    <label>Siguenos en Twitter </label>
                    </a>
                </div>
                <div class="row">
                    <a href="#"><img src="https://freeiconshop.com/wp-content/uploads/edd/pinterest-flat.png" alt="">
                    <label>Siguenos en Pinterest </label>
                    </a>
                </div>
                <div class="row">
                    <a href="#"><img src="https://cdn.iconscout.com/icon/free/png-256/youtube-85-226402.png" alt="">
                    <label>Siguenos en Youtube </label>
                    </a>
                </div>
                <div class="row">
                    <a href="#"><img src="https://cdn.iconscout.com/icon/free/png-256/instagram-1865894-1581910.png" alt="">
                    <label>Siguenos en Instagram </label>
                    </a>
                </div>
            </div>

            <div class="footer-column3">

                <h1>Información de Contacto</h1>
                <div class="row2-location">
                    <img src="https://icon-library.com/images/home-icon-white-png/home-icon-white-png-6.jpg" alt="">
                    <label> C: Gonzalo Jimenez de Quesada, 2 </label>
                </div>
                <div class="row2">
                 <img src="https://icon-library.com/images/phone-icon-white-png/phone-icon-white-png-17.jpg" alt="">
                    <label> 649 73 78 25 </label>
                </div>
                <div class="row2">
                <img src="https://www.pngkit.com/png/full/222-2226274_white-email-icon-png-download-mail-symbol-png.png" alt="">
                    <label> Qplan@gmail.com </label>
                </div>

            </div>
        </div>
        
    </div>
    <div class="footer-container-footer">
        <div class="footer-align">
            <div class="copyright">
                Copyright @2021 QPlan. Todos los derechos reservados
            </div>
            <div class="informacion">
                <a href="#">Información compañia</a>
                <a href="#">Privacion y Politica</a>
                <a href="">Terminos y condiciones</a>
            </div>
        </div>
    </div>
</footer>