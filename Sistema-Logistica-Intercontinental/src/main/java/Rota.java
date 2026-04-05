import java.time.*;
import java.time.format.DateTimeFormatter;

public class Rota {
    private ZonedDateTime dataPartida;
    private OffsetDateTime dataPrevista;
    private final Duration duration = Duration.ofHours(10);
    private ZoneOffset offsetDestino;
    private DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm XXX");

    public Rota(String dataCompleta, String fusoDestino){
        this.dataPartida = formattedDate(dataCompleta);
        this.offsetDestino= ZoneOffset.of(fusoDestino);
        this.dataPrevista = null;
    }

    private ZonedDateTime formattedDate(String dataCompleta){
        return ZonedDateTime.parse(dataCompleta,parser);
    }

    public void calculoTempoRota(){
        ZonedDateTime dataDestino = this.dataPartida.plus(this.duration);
        this.dataPrevista = dataDestino.withZoneSameInstant(this.offsetDestino).toOffsetDateTime();
    }

    public void relatorioEntrega(){
        System.out.println("Data de Partida: " + dataPartida.format(parser));
        System.out.println("Data prevista chegada: " + dataPrevista.format(parser));

    }
}
