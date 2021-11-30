package co.misiontic.p53.account_ms.models;

public enum TiposDocumento {
    Cedula("CC"),
    TarjetaIdentidad("TI"),
    Pasaporte("PP"),
    ;

    public final String SIGLA;
    
    private TiposDocumento(String sigla) {
        this.SIGLA = sigla;
    }
}
