import java.util.Scanner;

public class Main {
    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Qual a data de envio do pedido: (escreva no seguinte formato" +
                "(dd/MM/yyyy)): ");

        String dataEnvio = sc.nextLine();
        System.out.println("Qual o horario de partida (escreva no seguinte formato" +
                "HH:mm): ");

        String horarioPartida= sc.nextLine();
        System.out.println("Qual o fuso de origem em relação a Greenwich? (Use o formato ±HH:mm, ex: -03:00 ou +01:00): ");

        String fusoOrigem= sc.nextLine();

        System.out.println("Qual o fuso de destino em relação a Greenwich? (Use o formato ±HH:mm, ex: -03:00 ou +01:00): ");
        String fusoDestino= sc.nextLine();

        String dataCompleta = String.join(" ", dataEnvio,horarioPartida,fusoOrigem).trim();
        System.out.println(dataCompleta);

        Rota rota = new Rota(dataCompleta,fusoDestino);

        rota.calculoTempoRota();
        rota.relatorioEntrega();
    }
}
