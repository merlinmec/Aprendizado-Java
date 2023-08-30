package mecdream;

public class Porta {
    String cor;
    double dimensaox;
    double dimensaoy;
    double dimensaoz;
    boolean aberta;

    void abre() {
        this.aberta = true;
    }

    void fecha() {
        this.aberta = false;
    }

    void pinta(String s) {
        System.out.println("Porta pintada de " + s);
    }

    boolean estaAberta() {
        if (this.aberta == true) {
            System.out.println("Porta esta aberta");
            return true;
        } else {
            System.out.println("Porta nao esta aberta");
            return false;
        }
    }
}
