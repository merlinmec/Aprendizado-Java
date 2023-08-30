package mecdream;

public class Main {
	
	public static void main(String[] args) {
	
        Pessoa pessoinha;
        pessoinha = new Pessoa();

        pessoinha.nome = "Maria";
        pessoinha.idade = 22;
        pessoinha.fazAniversario();

        Porta portinha;
        portinha = new Porta();

        portinha.abre();
        portinha.fecha();
        portinha.abre();
        portinha.estaAberta();
        portinha.pinta("Rosa");
        portinha.dimensaox = 23.3;
        portinha.dimensaoy = 21.1;
        portinha.dimensaoz = 1.9;

		
	}
	
}
