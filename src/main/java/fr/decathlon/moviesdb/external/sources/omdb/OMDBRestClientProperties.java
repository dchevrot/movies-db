package fr.decathlon.moviesdb.external.sources.omdb;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "external-movies-sources.omdb")
public class OMDBRestClientProperties {

    @NotEmpty
    private String url;
    @NotEmpty
    private String apiKey;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}

