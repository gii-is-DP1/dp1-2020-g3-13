$(function() {
    //Lettering
    $('.nombre-sitio').lettering();
    $('.ocultar').hide();

    
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

