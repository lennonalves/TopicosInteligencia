/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RBC;

/**
 *
 * @author lennon
 */

/* lista de atributos do caso

idade da mulher (int)
educacao da mulher (1-4)
educacao do homem (1-4)
numero de criancas (int)
religiao da mulher (nao isla/isla)
emprego da mulher (sim/nao)
ocupacao do homem (1-4)
qualidade de vida (1-4)
exposição de mídia (boa/ruim)
método contraceptivo (nao/longo tempo/curto tempo)

fim da lista */

public class Casos {
    private int idademulher;
    private int educacaomulher;
    private int educacaohomem;
    private int numerocriancas;
    private int religiaomulher;
    private int empregomulher;
    private int ocupacaohomem;
    private int qualidadevida;
    private int exposicaomidia;
    private int metodo;
    
    public Casos(int idademulher, int educacaomulher, int educacaohomem, int numerocriancas, int religiaomulher, int empregomulher, int ocupacaohomem, int qualidadevida, int exposicaomidia, int metodo) {
        this.idademulher = idademulher;
        this.educacaomulher = educacaomulher;
        this.educacaohomem = educacaohomem;
        this.numerocriancas = numerocriancas;
        this.religiaomulher = religiaomulher;
        this.empregomulher = empregomulher;
        this.ocupacaohomem = ocupacaohomem;
        this.qualidadevida = qualidadevida;
        this.exposicaomidia = exposicaomidia;
        this.metodo = metodo;
    }

    Casos() {}
    
    public String toString() {
        String s = idademulher + "," 
                + educacaomulher + ","
                + educacaohomem + ","
                + numerocriancas + ","
                + religiaomulher + ","
                + empregomulher + ","
                + ocupacaohomem + ","
                + qualidadevida + ","
                + exposicaomidia + ","
                + metodo;
        return s;
    }
    
    /**
     * @return the idademulher
     */
    public int getIdademulher() {
        return idademulher;
    }

    /**
     * @param idademulher the idademulher to set
     */
    public void setIdademulher(int idademulher) {
        this.idademulher = idademulher;
    }

    /**
     * @return the educacaomulher
     */
    public int getEducacaomulher() {
        return educacaomulher;
    }

    /**
     * @param educacaomulher the educacaomulher to set
     */
    public void setEducacaomulher(int educacaomulher) {
        this.educacaomulher = educacaomulher;
    }

    /**
     * @return the educacaohomem
     */
    public int getEducacaohomem() {
        return educacaohomem;
    }

    /**
     * @param educacaohomem the educacaohomem to set
     */
    public void setEducacaohomem(int educacaohomem) {
        this.educacaohomem = educacaohomem;
    }

    /**
     * @return the numerocriancas
     */
    public int getNumerocriancas() {
        return numerocriancas;
    }

    /**
     * @param numerocriancas the numerocriancas to set
     */
    public void setNumerocriancas(int numerocriancas) {
        this.numerocriancas = numerocriancas;
    }

    /**
     * @return the religiaomulher
     */
    public int getReligiaomulher() {
        return religiaomulher;
    }

    /**
     * @param religiaomulher the religiaomulher to set
     */
    public void setReligiaomulher(int religiaomulher) {
        this.religiaomulher = religiaomulher;
    }

    /**
     * @return the empregomulher
     */
    public int getEmpregomulher() {
        return empregomulher;
    }

    /**
     * @param empregomulher the empregomulher to set
     */
    public void setEmpregomulher(int empregomulher) {
        this.empregomulher = empregomulher;
    }

    /**
     * @return the ocupacaohomem
     */
    public int getOcupacaohomem() {
        return ocupacaohomem;
    }

    /**
     * @param ocupacaohomem the ocupacaohomem to set
     */
    public void setOcupacaohomem(int ocupacaohomem) {
        this.ocupacaohomem = ocupacaohomem;
    }

    /**
     * @return the qualidadevida
     */
    public int getQualidadevida() {
        return qualidadevida;
    }

    /**
     * @param qualidadevida the qualidadevida to set
     */
    public void setQualidadevida(int qualidadevida) {
        this.qualidadevida = qualidadevida;
    }

    /**
     * @return the exposicaomidia
     */
    public int getExposicaomidia() {
        return exposicaomidia;
    }

    /**
     * @param exposicaomidia the exposicaomidia to set
     */
    public void setExposicaomidia(int exposicaomidia) {
        this.exposicaomidia = exposicaomidia;
    }

    /**
     * @return the metodo
     */
    public int getMetodo() {
        return metodo;
    }

    /**
     * @param metodo the metodo to set
     */
    public void setMetodo(int metodo) {
        this.metodo = metodo;
    }
}
