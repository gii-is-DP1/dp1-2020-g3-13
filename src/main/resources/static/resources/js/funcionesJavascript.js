

$(function() {
    //Lettering
    $('.nombre-sitio').lettering();
    $('.ocultar').hide();

    $("#datetimeInicio").datetimepicker({
        format: 'YYYY/MM/D HH:mm'
    });
    $("#datetimeFin").datetimepicker({
        format: 'YYYY/MM/D HH:mm'
    });


    
    return false;
});



function btn_click(nombreEvento, asunto, descripcion,fechaConsulta, email){
    $('#detallesTabla').show()
    $('#tituloDetalle').text(nombreEvento)
    $('#asuntoDetalle').text(asunto)
    $('#descripcionDetalle').text(descripcion)
    $('#fechaDetalle').text(fechaConsulta)
    $('#emailDetalle').text(email)
}

