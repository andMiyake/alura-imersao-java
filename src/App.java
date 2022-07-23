import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        String imdbApiKey = System.getenv("API_KEY");
        // System.out.println("API_KEY : " + imdbApiKey);

        ApiOwner api = ApiOwner.LANGUAGES;

        // Languages API
        String url = api.getURL();
        ContentExtractor extractor = api.getExtractor();

        var http = new ClientHttp();
        String json = http.searchData(url);

        // exibir e manipular os dados
        // show and manipulate the data

        List<Content> contents = extractor.extractContent(json);

        var stickerCreator = new StickerCreator();

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.imageUrl()).openStream();
            String fileName = content.title().replace(":", "-") + ".png";

            stickerCreator.create(inputStream, fileName);

            if (api == ApiOwner.IMDB) {
                System.out.println("Title: " + "\u001b[1m" + content.title() + "\u001b[0m");
                System.out.println("Poster: " + "\u001b[1m" + content.imageUrl() + "\u001b[0m");
                System.out.println("\u001b[46m" + "Rating: " + "\u001b[1m" + content.rating() + "\u001b[0m");
                int ratingStars = Integer.parseInt(content.rating().substring(0, 1));

                for (int j = 1; j <= ratingStars; j++) {
                    System.out.print("\u2B50");
                }
                System.out.println();
                System.out.println();
            } else {
                System.out.println("Title: " + "\u001b[1m" + content.title() + "\u001b[0m");
                System.out.println();
            }

        }

    }
}
