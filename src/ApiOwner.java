public enum ApiOwner {

    //IMDB API alura alternatives
    // IMDB("https://imdb-api.com/en/API/Top250Movies/", ImdbContentExtractor.class.getName()),
    // IMDB("https://imdb-api.com/en/API/MostPopularMovies/", ImdbContentExtractor.class.getName()),
    // IMDB("https://api.mocki.io/v2/549a5d8b/MostPopularMovies", ImdbContentExtractor.class.getName()),
    IMDB("https://api.mocki.io/v2/549a5d8b", ImdbContentExtractor.class.getName()),
    NASA("https://api.mocki.io/v2/549a5d8b/NASA-APOD", NasaContentExtractor.class.getName());

    private final String url;
    private final String extractor;

    private ApiOwner(String url, String extractor) {
        this.url = url;
        this.extractor = extractor;
    }

    public String getURL() {
        return url;
    }

    public String getExtractor() {
        return extractor;
    }
}
