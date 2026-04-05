package App;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GithubActivity {
    private String user;
    private JsonNode gitData;

    public GithubActivity(String nameUser){
        this.user = nameUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void fetchUser(){
        ObjectMapper mapper = new ObjectMapper();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("https://api.github.com/users/%s/events",this.user)))
                    .GET()
                    .build();

            this.gitData = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(jsonString -> {
                        try {
                            return mapper.readTree(jsonString);
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .join(); // O join() trava a execução até o objeto estar "na mão"
        }
    }

    public void printActivity(){
        if (this.gitData != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for(int i=0; i<5; i++){
                String date = gitData.get(i).get("created_at").asText();
                LocalDate localDate = LocalDate.parse(date,formatter);
                String typeEvent = gitData.get(i).get("type").asText();
                System.out.println();
            }
            String primeiroTipo = gitData.get(0).get("type").asText();
            System.out.println("Tipo armazenado: " + primeiroTipo);
        }
    }

}
