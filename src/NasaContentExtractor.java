import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> extractContent(String json) {

        // extract only the data that matters (title, image)
        var jsonParser = new JsonParser();
        List<Map<String, String>> attributeList = jsonParser.parse(json);
        List<Content> contents = new ArrayList<>();

        attributeList.forEach((attribute) -> {
            String title = attribute.get("title");
            String imageUrl = attribute.get("url");
            var content = new Content(title, imageUrl, "");
            contents.add(content);
        });

        return contents;
    }
}
