package org.springframework.samples.petclinic.model;

public enum NombreTiposEntrada {
    DIURNA("Diurna"), NOCTURNA("Nocturna"), PASE_UN_DIA("Pase de un solo día"), PASE_VARIOS_DIAS("Pase de varios días");

    private String muestraNombre;

    NombreTiposEntrada(String muestraNombre) {
        this.muestraNombre = muestraNombre;
    }

    public String displayName() { return muestraNombre; }


}
