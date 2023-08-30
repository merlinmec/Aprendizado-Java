package exerciciosbasicos;

public class Exercicio5 {
    public static void main(String[] args) {
        int n1 = 0, n2 = 1, n3;
        System.out.print(n1 + ", " + n2);

        n3 = n1 + n2;
        while (n3 <= 100) {
            System.out.print(", " + n3);
            n1 = n2;
            n2 = n3;
            n3 = n1 + n2;
        }
    }
}
