public class TestaAlgunsMetodos {
    public static void main(String[] args) {
        class Conta {
            int numero;
            String titular;
            double saldo;

            void saca(double quantidade) {
                double novoSaldo = this.saldo - quantidade;
                this.saldo = novoSaldo;
            }

            void deposita(double quantidade) {
                this.saldo += quantidade;
            }

        }

        Conta minhaConta;
        minhaConta = new Conta();

        minhaConta.titular = "Felipe";
        minhaConta.saldo = 1000.0;
        minhaConta.saca(200);
        minhaConta.deposita(500);

        System.out.println("O saldo da minha conta é de " + minhaConta.saldo);
    }
}
