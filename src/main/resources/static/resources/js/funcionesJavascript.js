

$(function() {
    //Lettering
    $('.nombre-sitio').lettering();
    $('.ocultar').hide();


    $("#fechaInicio").datepicker({
            dateFormat: 'yy/mm/dd'
    });
    $("#fechaFin").datepicker({
            dateFormat: 'yy/mm/dd'
    });


    $("#datetimeInicio").datetimepicker({
        format: 'YYYY/MM/DD HH:mm'
    });
    $("#datetimeFin").datetimepicker({
        format: 'YYYY/MM/DD HH:mm'
    });
    
    if($('.SinRespuestaConsulta').text() == ""){
        $('.SinRespuestaConsulta').text("Aún no hay una respuesta para esta consulta");
    }
    $("div[id*='javascriptActividad']").hide();

    $( "#datetimeInicio").focusout(function(){
        i = 0;
        if(typeof($('#datetimeFin').data('date')) != "undefined"){
            console.log('hi')
            $("div[id*='javascriptActividad']").show()
            while($("div[id*='javascriptActividad']").length > i){
                fechaActividadIterada = new Date($("div[id*='javascriptActividad']").eq(i).attr('data'));
                fechaIntroducidaInicio = new Date($('#datetimeInicio').data('date'));
                fechaIntroducidaFinal = new Date($('#datetimeFin').data('date'));
                if(fechaActividadIterada >= fechaIntroducidaInicio && fechaActividadIterada < fechaIntroducidaFinal){    
                    $("div[id*='javascriptActividad']").eq(i).show()
                }else{
                    $("div[id*='javascriptActividad']").eq(i).hide()
                }
                    i++;
            }
    }
    })

    $( "#datetimeFin").focusout(function(){
        i = 0;
        if(typeof($('#datetimeInicio').data('date')) != "undefined"){
            console.log('hi')
            $("div[id*='javascriptActividad']").show()
            while($("div[id*='javascriptActividad']").length > i){
                fechaActividadIterada = new Date($("div[id*='javascriptActividad']").eq(i).attr('data'));
                fechaIntroducidaInicio = new Date($('#datetimeInicio').data('date'));
                fechaIntroducidaFinal = new Date($('#datetimeFin').data('date'));
                if(fechaActividadIterada >= fechaIntroducidaInicio && fechaActividadIterada < fechaIntroducidaFinal){    
                    $("div[id*='javascriptActividad']").eq(i).show()
                }else{
                    $("div[id*='javascriptActividad']").eq(i).hide()
                }
                    i++;
            }
    }
    })


   
    
    return false;
});



function miselect(idRecinto){

    x = idRecinto.value;
    $('#nombreRecintoSeleccionado').text($('.'+x+'nombreRecintoSeleccionado').text())
    $('#aforoSeleccionado').text($('.'+x+'aforoSeleccionado').text())
    $('#direccionSeleccionado').text($('.'+x+'direccionSeleccionado').text())
    $('#emailSeleccionado').text($('.'+x+'emailSeleccionado').text())
    $('#telefonoContacto').text($('.'+x+'telefonoContacto').text())
    $('#caracteristicasSeleccionado').text($('.'+x+'caracteristicasSeleccionado').text())
    $('#precioSeleccionado').text($('.'+x+'precioSeleccionado').text())

    
}


function btn_click(nombreEvento, asunto, descripcion,fechaConsulta, email){
    $('#detallesTabla').show()
    $('#tituloDetalle').text(nombreEvento)
    $('#asuntoDetalle').text(asunto)
    $('#descripcionDetalle').text(descripcion)
    $('#fechaDetalle').text(fechaConsulta)
    $('#emailDetalle').text(email)
}


