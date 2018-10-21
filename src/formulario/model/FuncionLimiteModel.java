package formulario.model;

public class FuncionLimiteModel {
    private String funcion;
    private Double id;
    private String limiteInicial;
    private String limiteFinal;

    public String getLimiteFinal() {
        return limiteFinal;
    }

    public void setLimiteFinal(String limiteFinal) {
        this.limiteFinal = limiteFinal;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getLimiteInicial() {
        return limiteInicial;
    }

    public void setLimiteInicial(String limiteInicial) {
        this.limiteInicial = limiteInicial;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }
}
