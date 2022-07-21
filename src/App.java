import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        String imdbApiKey = System.getenv("API_KEY");
        // System.out.println("API_KEY : " + imdbApiKey);

        // fazer uma conexão HTTP e buscar os top 250 filmes
        // make an HTTP conection and search the top 250 movies

        // IMDB API
        // String url = ApiOwner.IMDB.getURL();
        // ContentExtractor extractor = new ImdbContentExtractor();

        // Nasa API
        String url = ApiOwner.NASA.getURL();
        ContentExtractor extractor = new NasaContentExtractor();

        var http = new ClientHttp();
        String json = http.searchData(url);

        // exibir e manipular os dados
        // show and manipulate the data

        List<Content> contents = extractor.extractContent(json);

        var creator = new StickerCreator();

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.imageUrl()).openStream();
            String fileName = content.title() + ".png";

            creator.create(inputStream, fileName);

            // System.out.println("Title: " + "\u001b[1m" + content.get("title") +
            // "\u001b[0m");
            // System.out.println("Poster: " + "\u001b[1m" + content.get("image") +
            // "\u001b[0m");
            // System.out.println("\u001b[46m" + "Rating: " + "\u001b[1m" +
            // content.get("imDbRating") + "\u001b[0m");

            // int ratingStars = Integer.parseInt(content.get("imDbRating").substring(0,
            // 1));

            // for (int i = 1; i <= ratingStars; i++) {
            // System.out.print("\u2B50");
            // }
            // System.out.println();
            // System.out.println();

            System.out.println("Title: " + "\u001b[1m" + content.title() + "\u001b[0m");
            System.out.println();
        }

    }
}
