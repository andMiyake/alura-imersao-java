public enum ApiOwner {

    //IMDB API alura alternatives
    // IMDB("https://imdb-api.com/en/API/Top250Movies/", new ImdbContentExtractor()),
    // IMDB("https://imdb-api.com/en/API/MostPopularMovies/", new ImdbContentExtractor()),
    // IMDB("https://api.mocki.io/v2/549a5d8b/MostPopularMovies", new ImdbContentExtractor()),
    IMDB("https://api.mocki.io/v2/549a5d8b", new ImdbContentExtractor()),
    NASA("https://api.mocki.io/v2/549a5d8b/NASA-APOD", new NasaContentExtractor()),
    LANGUAGES("https://alura-languages-api-afm.herokuapp.com/languages", new LanguagesContentExtractor());

    private final String url;
    private final ContentExtractor extractor;

    private ApiOwner(String url, ContentExtractor extractor) {
        this.url = url;
        this.extractor = extractor;
    }

    public String getURL() {
        return url;
    }

    public ContentExtractor getExtractor() {
        return extractor;
    }
}
