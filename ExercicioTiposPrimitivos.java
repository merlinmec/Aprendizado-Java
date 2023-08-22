public class ExercicioTiposPrimitivos {
    public static void main(String[] args) {
        int gastoJan = 15000;
        int gastoFev = 23000;
        int gastoMar = 17000;
        int despTri = gastoJan + gastoFev + gastoMar;
        double mediaMen = despTri / 3;

        System.out.println("O gasto trimentral foi de " + despTri);
        System.out.println("A média de gastos mensal foi de " + mediaMen);

    }
}
