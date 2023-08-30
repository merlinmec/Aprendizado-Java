package testeconta;

public class Conta {
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
