package fr.decathlon.moviesdb.external.sources.config;

import fr.decathlon.moviesdb.MovieSource;
import fr.decathlon.moviesdb.external.sources.omdb.OMDBRestClient;
import fr.decathlon.moviesdb.external.sources.omdb.OMDBRestClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OMDBRestClientProperties.class)
public class ExternalSourcesConfiguration {

    @Bean
    public MovieSource OMDBMovieSource(OMDBRestClientProperties omdbProperties) {
        return new OMDBRestClient(omdbProperties);
    }
}
