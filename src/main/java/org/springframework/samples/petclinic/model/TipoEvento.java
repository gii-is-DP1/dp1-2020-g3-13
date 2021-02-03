package org.springframework.samples.petclinic.model;

public enum TipoEvento {
    SOCIALES("Sociales"), CORPORATIVOS_EMPRESARIALES("Corporativos o empresariales"), ESPIRITUALES_O_COMUNITARIOS("Espirituales o comunitarios"), EVENTOS_DEPORTIVOS("Eventos deportivos"), ACADEMICOS("Académicos"), CULTURALES_DE_OCIO("Culturales y de ocio"), POLITICOS("Políticos"), EDUCATIVOS("Educativos"), OTROS("Otros") ;

    private String muestraNombre;

    TipoEvento(String muestraNombre) {
        this.muestraNombre = muestraNombre;
    }

    public String displayName() { return muestraNombre; }
    
}
