package org.springframework.samples.petclinic.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.samples.petclinic.model.Organizacion;
import org.springframework.stereotype.Service;

import jdk.internal.org.jline.utils.Log;
//Para ver la configuración de esta clase debe de mirarse application.properties y buscar el apartado de Email
//Se utilizan dependecias nuevas incluidas en el pom.xml
//Lineas 205 - 213
@Service
public class EnvioEmailService {
    //Si fijamos el valor requerido como falso, entonces en el momento de 'wiring' , Spring dejará el bean sin cablear si la dependencia no se resuelve. De acuerdo con las mejores prácticas de Spring, debemos evitar establecer el valor requerido
    @Autowired(required=false)
    private JavaMailSender mailSender;
    @Scope
    //Pasamos por parametro: destinatario, asunto y el mensaje
    public void sendEmailSimple(Organizacion organizacion, String subject, String content) {
        final MimeMessage message = mailSender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(organizacion.getEmail());
            helper.setSubject(subject); 
            helper.setText(content);
            helper.setFrom("dpqplan@gmail.com");

        } catch (MessagingException e) {
            new RuntimeException(e);
        }
        this.mailSender.send(message);

    }    
 //Envia un correo al destinatario dado con un asunto y contenido
     //Este metodo solo sirve para emails simples.
     public void sendEmailConFicheros(Organizacion organizacion, String subject, String content, File ficherosAdjuntos) {
        final MimeMessage message = mailSender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(organizacion.getEmail());
            helper.setSubject(subject); 
            helper.setText(content);
            helper.setFrom("dpqplan@gmail.com");

            //Adjuntando archivos
            if (ficherosAdjuntos != null) {
                for (int i = 0; i < ficherosAdjuntos.listFiles().length; i++) {
                    FileSystemResource file = new FileSystemResource(ficherosAdjuntos.listFiles()[i]);
                    helper.addAttachment(ficherosAdjuntos.listFiles()[i].getName(), file);
                    if (Log.isDebugEnabled()) {
                        Log.debug("Archivo" + file + " adjuntado");
                            
                    }
                        
                }
            }

        } catch (MessagingException e) {
            new RuntimeException(e);
        }
        this.mailSender.send(message);

    }     


}