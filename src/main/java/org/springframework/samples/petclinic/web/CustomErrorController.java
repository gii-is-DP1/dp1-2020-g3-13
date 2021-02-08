/*package org.springframework.samples.petclinic.web;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    
    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Obtiene el estado del error
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // Muestra el error en una página específica para este
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";

            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error-403";
            }

        }
        return "error";
    }

}

*/