public class Mec {
    public static void main(String[] args) {
        class Pessoa {
            String nome;
            int idade;

            void fazAniversario() {
                int novaIdade = this.idade + 1;
                this.idade = novaIdade;
                System.out.println("Parabens pelos seus " + this.idade + " anos!!!");
            }
        }

        class Porta {
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
