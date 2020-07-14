package fr.decathlon.moviesdb.external.sources.omdb;

import fr.decathlon.moviesdb.MovieSource;
import fr.decathlon.moviesdb.domain.Movie;
import fr.decathlon.moviesdb.external.sources.ExternalSourceQueryException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.Locale;
import java.util.Optional;

public class OMDBRestClient implements MovieSource {

    private static final int CONNECTION_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 10;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
    private final OMDBRestClientProperties omdbRestClientProperties;
    private final RestTemplate restClient;

    public OMDBRestClient(OMDBRestClientProperties omdbRestClientProperties) {
        this.omdbRestClientProperties = omdbRestClientProperties;
        this.restClient = new RestTemplateBuilder()
            .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
            .setConnectTimeout(Duration.of(CONNECTION_TIMEOUT, ChronoUnit.SECONDS))
            .setReadTimeout(Duration.of(READ_TIMEOUT, ChronoUnit.SECONDS))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(omdbRestClientProperties.getUrl())
            .queryParam("apikey", omdbRestClientProperties.getApiKey())
            .queryParam("t", title);

        try {
            String response = restClient.getForObject(builder.build().encode().toUri(), String.class);
            return Optional.ofNullable(toMovie(response));
        } catch (RestClientException e) {
            throw new ExternalSourceQueryException("Error calling omdb", e);
        }
    }

    private Movie toMovie(String stringToParse) {
        if(StringUtils.isEmpty(stringToParse)) {
            return null;
        }
        Movie movie = new Movie();
        try {
            JSONObject json = new JSONObject(stringToParse);
            movie.setTitle(json.getString("Title"));
            movie.setYear(json.getInt("Year"));
            movie.setRate(json.getString("Rated"));
            movie.setReleaseDate(LocalDate.from(DATE_FORMAT.parse(json.getString("Released"))));
            String runtimeStr = json.getString("Runtime");
            movie.setRuntime(Duration.of(Long.parseLong(runtimeStr.substring(0, runtimeStr.length() - 4)), ChronoUnit.SECONDS));
            movie.setGenre(json.getString("Genre"));
            movie.setDirector(json.getString("Director"));
            movie.setActors(json.getString("Actors"));
            movie.setPlot(json.getString("Plot"));
            movie.setLanguage(json.getString("Language"));
            movie.setAwards(json.getString("Awards"));
            movie.setPosterLink(json.getString("Poster"));
            movie.setRatings(json.getString("Ratings"));
            movie.setMetascore(json.getInt("Metascore"));
            movie.setImdbRating(json.getDouble("imdbRating"));
            movie.setImdbVotes(Integer.parseInt(json.getString("imdbVotes").replaceAll(",", "")));
            movie.setImdbId(json.getString("imdbID"));
            movie.setType(json.getString("Type"));
            movie.setDvdReleaseDate(LocalDate.from(DATE_FORMAT.parse(json.getString("DVD"))));
            String boxOfficeStr = json.getString("BoxOffice");
            movie.setBoxOffice(Integer.parseInt(boxOfficeStr.substring(1, boxOfficeStr.length()).replaceAll(",", "")));
            movie.setType(json.getString("Type"));
        } catch (JSONException e) {
            throw new ExternalSourceQueryException("Impossible to parse response from omdb", e);
        }
        return movie;
    }
}
