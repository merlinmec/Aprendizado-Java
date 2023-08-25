public class TesteConta {
    public static void main(String[] args) {
        class DataCalen {
            int dia;
            int mes;
            int ano;

            void formatada() {
                System.out.println(dia + "/" + mes + "/" + ano);
            }
        }

        class Conta {
            String titular;
            int numero;
            String agencia;
            double saldo;
            DataCalen dataAbertura;

            void saque(double quantidade) {
                double novoSaldo = this.saldo - quantidade;
                this.saldo = novoSaldo;
            }

            void deposito(double quantidade) {
                this.saldo += quantidade;
            }

            double calcularRendimento() {
                double rendimento = this.saldo * 0.1;
                return rendimento;
            }

            void recuperaDadosParaImpressao() {
                System.out.println(this.titular);
                System.out.println(this.saldo);
                System.out.println(this.agencia);
                dataAbertura.formatada();
                System.out.println(this.numero);
            }
        }

        Conta minhaConta;
        minhaConta = new Conta();
        DataCalen d = new DataCalen();
        minhaConta.dataAbertura = d;

        minhaConta.titular = "MecDream";
        minhaConta.saldo = 5000.0;
        minhaConta.agencia = "Tranquilona";
        minhaConta.dataAbertura.dia = 19;
        minhaConta.dataAbertura.mes = 7;
        minhaConta.dataAbertura.ano = 2001;
        minhaConta.numero = 19;
        minhaConta.deposito(600);
        minhaConta.saque(4000);
        minhaConta.calcularRendimento();

        minhaConta.recuperaDadosParaImpressao();
        System.out.println(minhaConta.calcularRendimento());

        Conta c1 = new Conta();
        c1.titular = "Danilo";
        c1.saldo = 100.0;

        Conta c2 = new Conta();
        c2.titular = "Danilo";
        c2.saldo = 100.0;

        // c2 = c1 tornará eles iguais, pois igualará a local deles

        if (c1 == c2) {
            System.out.println("iguais");
        } else {
            System.out.println("diferentes");
        }
    }
}
