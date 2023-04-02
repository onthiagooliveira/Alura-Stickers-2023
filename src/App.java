import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        /* Variável que armazena o link com a API do IMDB com os top 10 filmes */
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        /* Convertendo o URL em uma URI */
        URI endereco = URI.create(url);

        /* Instanciando o HttpClient para criar uma conexão com o link da api e acessar os dados */
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String>  response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        /* Extraindo os dados relevantes para um dicionário(Associa chave e valor)*/
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        /* Exibindo e manipulando os dados relevantes */
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println("Nota: " + filme.get("imDbRating"));
            System.out.println();
        }
    }
}