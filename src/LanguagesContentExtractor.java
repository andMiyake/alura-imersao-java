import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LanguagesContentExtractor implements ContentExtractor {
    public List<Content> extractContent(String json) {

        var jsonParser = new JsonParser();
        List<Map<String, String>> attributeList = jsonParser.parse(json);
        List<Content> contents = new ArrayList<>();

        attributeList.forEach((attribute) -> {
            String title = attribute.get("title");
            String imageUrl = attribute.get("image");
            var content = new Content(title, imageUrl, "");
            contents.add(content);
        });

        return contents;
    }
}
