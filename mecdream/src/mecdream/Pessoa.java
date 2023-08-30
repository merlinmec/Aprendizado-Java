package mecdream;

public class Pessoa {
    String nome;
    int idade;

    void fazAniversario() {
        int novaIdade = this.idade + 1;
        this.idade = novaIdade;
        System.out.println("Parabens pelos seus " + this.idade + " anos!!!");
    }
}
