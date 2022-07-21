import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> extractContent(String json) {

        // extrair só os dados que interessam (título, poster, classificação)
        // extract only the data that matters (title, poster, rating)
        var jsonParser = new JsonParser();
        List<Map<String, String>> attributeList = jsonParser.parse(json);
        List<Content> contents = new ArrayList<>();

        attributeList.forEach((attribute) -> {
            String title = attribute.get("title");
            String imageUrl = attribute.get("url");
            var content = new Content(title, imageUrl);
            contents.add(content);
        });

        return contents;
    }
}
