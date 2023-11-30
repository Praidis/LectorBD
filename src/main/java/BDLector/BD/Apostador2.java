package BDLector.BD;

public class Apostador2 {
    private String idapostador;
    private int vecesaparece;

    public Apostador2(String idapostador, int vecesaparece) {
        this.idapostador = idapostador;
        this.vecesaparece = vecesaparece;
    }

    public String getIdapostador() {
        return idapostador;
    }

    public int getVecesaparece() {
        return vecesaparece;
    }
}