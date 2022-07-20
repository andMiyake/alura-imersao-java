import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        String imdbApiKey = System.getenv("API_KEY");
        System.out.println("API_KEY : " + imdbApiKey);

        // fazer uma conexão HTTP e buscar os top 250 filmes
        // make an HTTP conection and search the top 250 movies

        // String url = "https://imdb-api.com/en/API/Top250Movies/" + imdbApiKey;
        // String url = "https://imdb-api.com/en/API/MostPopularMovies/" + imdbApiKey;

        // String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        String url = "https://api.mocki.io/v2/549a5d8b";
        var client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        var request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, poster, classificação)
        // extract only the data that matters (title, poster, rating)
        var jsonParser = new JsonParser();
        List<Map<String, String>> moviesList = jsonParser.parse(body);

        // exibir e manipular os dados
        // show and manipulate the data
        var creator = new StickerCreator();
        for (Map<String, String> movie : moviesList) {

            String urlImage = movie.get("image");
            String title = movie.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = title + ".png";

            creator.create(inputStream, fileName);

            // System.out.println("Title: " + "\u001b[1m" + movie.get("title") + "\u001b[0m");
            // System.out.println("Poster: " + "\u001b[1m" + movie.get("image") + "\u001b[0m");
            // System.out.println("\u001b[46m" + "Rating: " + "\u001b[1m" + movie.get("imDbRating") + "\u001b[0m");

            // int ratingStars = Integer.parseInt(movie.get("imDbRating").substring(0, 1));

            // for (int i = 1; i <= ratingStars; i++) {
            //     System.out.print("\u2B50");
            // }
            // System.out.println();
            // System.out.println();


            System.out.println("Title: " + "\u001b[1m" + title + "\u001b[0m");
            System.out.println();
        }

    }
}
