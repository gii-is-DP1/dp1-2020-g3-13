

$(function() {
    //Lettering
    $('.nombre-sitio').lettering();
    $('.ocultar').hide();

    $("#datetimeInicio").datetimepicker({
        format: 'YYYY/MM/DD HH:mm'
    });
    $("#datetimeFin").datetimepicker({
        format: 'YYYY/MM/DD HH:mm'
    });
    
    if($('.SinRespuestaConsulta').text() == ""){
        $('.SinRespuestaConsulta').text("Aún no hay una respuesta para esta consulta");
    }

    
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


