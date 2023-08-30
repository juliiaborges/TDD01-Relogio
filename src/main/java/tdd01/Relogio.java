package tdd01;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Relogio {
    private int horas;
    private int minutos;
    private int segundos;

    private DateTimeFormatter formato24h = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Relogio(int horas, int minutos, int segundos) {
        definirHorario(horas, minutos, segundos);
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public boolean formatoHorario() {
        if (horas < 0 || horas > 23) {
            return false;
        }
        if (minutos < 0 || minutos > 59) {
            return false;
        }
        if (segundos < 0 || segundos > 59) {
            return false;
        }
        return true;
    }

    private void definirHorario(int horas, int minutos, int segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public void reiniciar() {
        definirHorario(0, 0, 0);
    }

    public String marcarTempo(int horarioInicial, int minutosIniciais, int segundosIniciais, int horarioFim, int minutosFim, int segundosFim) {
        int segundosInicio = horarioInicial * 3600 + minutosIniciais * 60 + segundosIniciais;
        int segundosFimm = horarioFim * 3600 + minutosFim * 60 + segundosFim;
        int intervaloSegundos = segundosFimm - segundosInicio;

        if (intervaloSegundos < 0) {
            intervaloSegundos += 24 * 3600; // Adicionar um dia em segundos
        }

        int horasIntervalo = intervaloSegundos / 3600;
        int minutosIntervalo = (intervaloSegundos % 3600) / 60;
        int segundosIntervalo = intervaloSegundos % 60;

        return String.format("%02d:%02d:%02d", horasIntervalo, minutosIntervalo, segundosIntervalo);
    }

    public String imprimir24h(boolean formato) {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public String imprimirAMPM(boolean formato) {
        int horasAMPM = horas;
        String periodo = (horasAMPM < 12) ? "AM" : "PM";
        if (horasAMPM == 0) {
            horasAMPM = 12;
        } else if (horasAMPM > 12) {
            horasAMPM -= 12;
        }
        return String.format("%02d:%02d %s", horasAMPM, minutos, periodo);
    }

    public void atualizarHorario(int horas, int minutos, int segundos) {
        definirHorario(horas, minutos, segundos);
    }

    public String obterHorarioFormato24h() {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public String atualizarHora24h() {
        LocalTime localTime = LocalTime.now();
        this.horas = localTime.getHour();
        this.minutos = localTime.getMinute();
        this.segundos = localTime.getSecond();

        return formato24h.format(localTime);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

        Scanner scanner = new Scanner(System.in);
        Relogio relogio = new Relogio(12, 0, 0);

        System.out.println("Bem-vindo ao Relógio Digital!");

        int choice;
        do {
            System.out.println("\n--------Escolha uma opção: ----------------------");
            System.out.println("1. Definir horário");
            System.out.println("2. Reiniciar relógio");
            System.out.println("3. Marcar intervalo de tempo");
            System.out.println("4. Imprimir horário no formato 24h");
            System.out.println("5. Imprimir horário no formato AM/PM");
            System.out.println("6. Atualizar horário");
            System.out.println("7. Sair.");
            System.out.print("Opção: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Digite a hora: ");
                    int horas = scanner.nextInt();
                    System.out.print("Digite os minutos: ");
                    int minutos = scanner.nextInt();
                    System.out.print("Digite os segundos: ");
                    int segundos = scanner.nextInt();
                    relogio.definirHorario(horas, minutos, segundos);
                    break;

                case 2:
                    relogio.reiniciar();
                    System.out.println("Relógio reiniciado para meia-noite.");
                    break;

                case 3:
                    System.out.print("Digite o horário inicial do intervalo: ");
                    int inicioHoras = scanner.nextInt();
                    System.out.print("Digite os minutos iniciais do intervalo: ");
                    int inicioMinutos = scanner.nextInt();
                    System.out.print("Digite os segundos iniciais do intervalo: ");
                    int inicioSegundos = scanner.nextInt();

                    System.out.print("Digite o horário final do intervalo: ");
                    int fimHoras = scanner.nextInt();
                    System.out.print("Digite os minutos finais do intervalo: ");
                    int fimMinutos = scanner.nextInt();
                    System.out.print("Digite os segundos finais do intervalo: ");
                    int fimSegundos = scanner.nextInt();

                    String intervalo = relogio.marcarTempo(inicioHoras, inicioMinutos, inicioSegundos, fimHoras, fimMinutos, fimSegundos);
                    System.out.println("O intervalo de tempo decorrido é: " + intervalo);
                    break;

                case 4:
                    System.out.println("Horário no formato 24h: " + relogio.imprimir24h(true));
                    break;

                case 5:
                    System.out.println("Horário no formato AM/PM: " + relogio.imprimirAMPM(true));
                    break;

                case 6:
                        System.out.println("Hora atual atualizada: " + relogio.atualizarHora24h());
                    break;
                case 7:
                System.out.println("Fim do programa!");
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
