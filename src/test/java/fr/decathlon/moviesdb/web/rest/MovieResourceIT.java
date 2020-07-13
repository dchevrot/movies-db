package fr.decathlon.moviesdb.web.rest;

import fr.decathlon.moviesdb.MoviesDbApp;
import fr.decathlon.moviesdb.domain.Movie;
import fr.decathlon.moviesdb.repository.MovieRepository;
import fr.decathlon.moviesdb.service.MovieService;
import fr.decathlon.moviesdb.service.dto.MovieDTO;
import fr.decathlon.moviesdb.service.mapper.MovieMapper;
import fr.decathlon.moviesdb.service.dto.MovieCriteria;
import fr.decathlon.moviesdb.service.MovieQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MovieResource} REST controller.
 */
@SpringBootTest(classes = MoviesDbApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MovieResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;
    private static final Integer SMALLER_YEAR = 1 - 1;

    private static final String DEFAULT_RATE = "AAAAAAAAAA";
    private static final String UPDATED_RATE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RELEASE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RELEASE_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_RELEASE_DATE = LocalDate.ofEpochDay(-1L);

    private static final Duration DEFAULT_RUNTIME = Duration.ofHours(6);
    private static final Duration UPDATED_RUNTIME = Duration.ofHours(12);
    private static final Duration SMALLER_RUNTIME = Duration.ofHours(5);

    private static final String DEFAULT_GENRE = "AAAAAAAAAA";
    private static final String UPDATED_GENRE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECTOR = "AAAAAAAAAA";
    private static final String UPDATED_DIRECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_WRITERS = "AAAAAAAAAA";
    private static final String UPDATED_WRITERS = "BBBBBBBBBB";

    private static final String DEFAULT_ACTORS = "AAAAAAAAAA";
    private static final String UPDATED_ACTORS = "BBBBBBBBBB";

    private static final String DEFAULT_PLOT = "AAAAAAAAAA";
    private static final String UPDATED_PLOT = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_AWARDS = "AAAAAAAAAA";
    private static final String UPDATED_AWARDS = "BBBBBBBBBB";

    private static final String DEFAULT_POSTER_LINK = "AAAAAAAAAA";
    private static final String UPDATED_POSTER_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_RATINGS = "AAAAAAAAAA";
    private static final String UPDATED_RATINGS = "BBBBBBBBBB";

    private static final Integer DEFAULT_METASCORE = 1;
    private static final Integer UPDATED_METASCORE = 2;
    private static final Integer SMALLER_METASCORE = 1 - 1;

    private static final Double DEFAULT_IMDB_RATING = 1D;
    private static final Double UPDATED_IMDB_RATING = 2D;
    private static final Double SMALLER_IMDB_RATING = 1D - 1D;

    private static final Integer DEFAULT_IMDB_VOTES = 1;
    private static final Integer UPDATED_IMDB_VOTES = 2;
    private static final Integer SMALLER_IMDB_VOTES = 1 - 1;

    private static final String DEFAULT_IMDB_ID = "AAAAAAAAAA";
    private static final String UPDATED_IMDB_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DVD_RELEASE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DVD_RELEASE_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DVD_RELEASE_DATE = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_BOX_OFFICE = 1;
    private static final Integer UPDATED_BOX_OFFICE = 2;
    private static final Integer SMALLER_BOX_OFFICE = 1 - 1;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieQueryService movieQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMovieMockMvc;

    private Movie movie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Movie createEntity(EntityManager em) {
        Movie movie = new Movie()
            .title(DEFAULT_TITLE)
            .year(DEFAULT_YEAR)
            .rate(DEFAULT_RATE)
            .releaseDate(DEFAULT_RELEASE_DATE)
            .runtime(DEFAULT_RUNTIME)
            .genre(DEFAULT_GENRE)
            .director(DEFAULT_DIRECTOR)
            .writers(DEFAULT_WRITERS)
            .actors(DEFAULT_ACTORS)
            .plot(DEFAULT_PLOT)
            .language(DEFAULT_LANGUAGE)
            .country(DEFAULT_COUNTRY)
            .awards(DEFAULT_AWARDS)
            .posterLink(DEFAULT_POSTER_LINK)
            .ratings(DEFAULT_RATINGS)
            .metascore(DEFAULT_METASCORE)
            .imdbRating(DEFAULT_IMDB_RATING)
            .imdbVotes(DEFAULT_IMDB_VOTES)
            .imdbId(DEFAULT_IMDB_ID)
            .type(DEFAULT_TYPE)
            .dvdReleaseDate(DEFAULT_DVD_RELEASE_DATE)
            .boxOffice(DEFAULT_BOX_OFFICE);
        return movie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Movie createUpdatedEntity(EntityManager em) {
        Movie movie = new Movie()
            .title(UPDATED_TITLE)
            .year(UPDATED_YEAR)
            .rate(UPDATED_RATE)
            .releaseDate(UPDATED_RELEASE_DATE)
            .runtime(UPDATED_RUNTIME)
            .genre(UPDATED_GENRE)
            .director(UPDATED_DIRECTOR)
            .writers(UPDATED_WRITERS)
            .actors(UPDATED_ACTORS)
            .plot(UPDATED_PLOT)
            .language(UPDATED_LANGUAGE)
            .country(UPDATED_COUNTRY)
            .awards(UPDATED_AWARDS)
            .posterLink(UPDATED_POSTER_LINK)
            .ratings(UPDATED_RATINGS)
            .metascore(UPDATED_METASCORE)
            .imdbRating(UPDATED_IMDB_RATING)
            .imdbVotes(UPDATED_IMDB_VOTES)
            .imdbId(UPDATED_IMDB_ID)
            .type(UPDATED_TYPE)
            .dvdReleaseDate(UPDATED_DVD_RELEASE_DATE)
            .boxOffice(UPDATED_BOX_OFFICE);
        return movie;
    }

    @BeforeEach
    public void initTest() {
        movie = createEntity(em);
    }

    @Test
    @Transactional
    public void createMovie() throws Exception {
        int databaseSizeBeforeCreate = movieRepository.findAll().size();
        // Create the Movie
        MovieDTO movieDTO = movieMapper.toDto(movie);
        restMovieMockMvc.perform(post("/api/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(movieDTO)))
            .andExpect(status().isCreated());

        // Validate the Movie in the database
        List<Movie> movieList = movieRepository.findAll();
        assertThat(movieList).hasSize(databaseSizeBeforeCreate + 1);
        Movie testMovie = movieList.get(movieList.size() - 1);
        assertThat(testMovie.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMovie.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testMovie.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testMovie.getReleaseDate()).isEqualTo(DEFAULT_RELEASE_DATE);
        assertThat(testMovie.getRuntime()).isEqualTo(DEFAULT_RUNTIME);
        assertThat(testMovie.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testMovie.getDirector()).isEqualTo(DEFAULT_DIRECTOR);
        assertThat(testMovie.getWriters()).isEqualTo(DEFAULT_WRITERS);
        assertThat(testMovie.getActors()).isEqualTo(DEFAULT_ACTORS);
        assertThat(testMovie.getPlot()).isEqualTo(DEFAULT_PLOT);
        assertThat(testMovie.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testMovie.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testMovie.getAwards()).isEqualTo(DEFAULT_AWARDS);
        assertThat(testMovie.getPosterLink()).isEqualTo(DEFAULT_POSTER_LINK);
        assertThat(testMovie.getRatings()).isEqualTo(DEFAULT_RATINGS);
        assertThat(testMovie.getMetascore()).isEqualTo(DEFAULT_METASCORE);
        assertThat(testMovie.getImdbRating()).isEqualTo(DEFAULT_IMDB_RATING);
        assertThat(testMovie.getImdbVotes()).isEqualTo(DEFAULT_IMDB_VOTES);
        assertThat(testMovie.getImdbId()).isEqualTo(DEFAULT_IMDB_ID);
        assertThat(testMovie.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMovie.getDvdReleaseDate()).isEqualTo(DEFAULT_DVD_RELEASE_DATE);
        assertThat(testMovie.getBoxOffice()).isEqualTo(DEFAULT_BOX_OFFICE);
    }

    @Test
    @Transactional
    public void createMovieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = movieRepository.findAll().size();

        // Create the Movie with an existing ID
        movie.setId(1L);
        MovieDTO movieDTO = movieMapper.toDto(movie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovieMockMvc.perform(post("/api/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(movieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Movie in the database
        List<Movie> movieList = movieRepository.findAll();
        assertThat(movieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = movieRepository.findAll().size();
        // set the field null
        movie.setTitle(null);

        // Create the Movie, which fails.
        MovieDTO movieDTO = movieMapper.toDto(movie);


        restMovieMockMvc.perform(post("/api/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(movieDTO)))
            .andExpect(status().isBadRequest());

        List<Movie> movieList = movieRepository.findAll();
        assertThat(movieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkImdbIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = movieRepository.findAll().size();
        // set the field null
        movie.setImdbId(null);

        // Create the Movie, which fails.
        MovieDTO movieDTO = movieMapper.toDto(movie);


        restMovieMockMvc.perform(post("/api/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(movieDTO)))
            .andExpect(status().isBadRequest());

        List<Movie> movieList = movieRepository.findAll();
        assertThat(movieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMovies() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList
        restMovieMockMvc.perform(get("/api/movies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movie.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].releaseDate").value(hasItem(DEFAULT_RELEASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].runtime").value(hasItem(DEFAULT_RUNTIME.toString())))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].director").value(hasItem(DEFAULT_DIRECTOR)))
            .andExpect(jsonPath("$.[*].writers").value(hasItem(DEFAULT_WRITERS)))
            .andExpect(jsonPath("$.[*].actors").value(hasItem(DEFAULT_ACTORS)))
            .andExpect(jsonPath("$.[*].plot").value(hasItem(DEFAULT_PLOT)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].awards").value(hasItem(DEFAULT_AWARDS)))
            .andExpect(jsonPath("$.[*].posterLink").value(hasItem(DEFAULT_POSTER_LINK)))
            .andExpect(jsonPath("$.[*].ratings").value(hasItem(DEFAULT_RATINGS)))
            .andExpect(jsonPath("$.[*].metascore").value(hasItem(DEFAULT_METASCORE)))
            .andExpect(jsonPath("$.[*].imdbRating").value(hasItem(DEFAULT_IMDB_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].imdbVotes").value(hasItem(DEFAULT_IMDB_VOTES)))
            .andExpect(jsonPath("$.[*].imdbId").value(hasItem(DEFAULT_IMDB_ID)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].dvdReleaseDate").value(hasItem(DEFAULT_DVD_RELEASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].boxOffice").value(hasItem(DEFAULT_BOX_OFFICE)));
    }
    
    @Test
    @Transactional
    public void getMovie() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get the movie
        restMovieMockMvc.perform(get("/api/movies/{id}", movie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(movie.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE))
            .andExpect(jsonPath("$.releaseDate").value(DEFAULT_RELEASE_DATE.toString()))
            .andExpect(jsonPath("$.runtime").value(DEFAULT_RUNTIME.toString()))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE))
            .andExpect(jsonPath("$.director").value(DEFAULT_DIRECTOR))
            .andExpect(jsonPath("$.writers").value(DEFAULT_WRITERS))
            .andExpect(jsonPath("$.actors").value(DEFAULT_ACTORS))
            .andExpect(jsonPath("$.plot").value(DEFAULT_PLOT))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.awards").value(DEFAULT_AWARDS))
            .andExpect(jsonPath("$.posterLink").value(DEFAULT_POSTER_LINK))
            .andExpect(jsonPath("$.ratings").value(DEFAULT_RATINGS))
            .andExpect(jsonPath("$.metascore").value(DEFAULT_METASCORE))
            .andExpect(jsonPath("$.imdbRating").value(DEFAULT_IMDB_RATING.doubleValue()))
            .andExpect(jsonPath("$.imdbVotes").value(DEFAULT_IMDB_VOTES))
            .andExpect(jsonPath("$.imdbId").value(DEFAULT_IMDB_ID))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.dvdReleaseDate").value(DEFAULT_DVD_RELEASE_DATE.toString()))
            .andExpect(jsonPath("$.boxOffice").value(DEFAULT_BOX_OFFICE));
    }


    @Test
    @Transactional
    public void getMoviesByIdFiltering() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        Long id = movie.getId();

        defaultMovieShouldBeFound("id.equals=" + id);
        defaultMovieShouldNotBeFound("id.notEquals=" + id);

        defaultMovieShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultMovieShouldNotBeFound("id.greaterThan=" + id);

        defaultMovieShouldBeFound("id.lessThanOrEqual=" + id);
        defaultMovieShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllMoviesByTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where title equals to DEFAULT_TITLE
        defaultMovieShouldBeFound("title.equals=" + DEFAULT_TITLE);

        // Get all the movieList where title equals to UPDATED_TITLE
        defaultMovieShouldNotBeFound("title.equals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllMoviesByTitleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where title not equals to DEFAULT_TITLE
        defaultMovieShouldNotBeFound("title.notEquals=" + DEFAULT_TITLE);

        // Get all the movieList where title not equals to UPDATED_TITLE
        defaultMovieShouldBeFound("title.notEquals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllMoviesByTitleIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where title in DEFAULT_TITLE or UPDATED_TITLE
        defaultMovieShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

        // Get all the movieList where title equals to UPDATED_TITLE
        defaultMovieShouldNotBeFound("title.in=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllMoviesByTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where title is not null
        defaultMovieShouldBeFound("title.specified=true");

        // Get all the movieList where title is null
        defaultMovieShouldNotBeFound("title.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByTitleContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where title contains DEFAULT_TITLE
        defaultMovieShouldBeFound("title.contains=" + DEFAULT_TITLE);

        // Get all the movieList where title contains UPDATED_TITLE
        defaultMovieShouldNotBeFound("title.contains=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllMoviesByTitleNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where title does not contain DEFAULT_TITLE
        defaultMovieShouldNotBeFound("title.doesNotContain=" + DEFAULT_TITLE);

        // Get all the movieList where title does not contain UPDATED_TITLE
        defaultMovieShouldBeFound("title.doesNotContain=" + UPDATED_TITLE);
    }


    @Test
    @Transactional
    public void getAllMoviesByYearIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where year equals to DEFAULT_YEAR
        defaultMovieShouldBeFound("year.equals=" + DEFAULT_YEAR);

        // Get all the movieList where year equals to UPDATED_YEAR
        defaultMovieShouldNotBeFound("year.equals=" + UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void getAllMoviesByYearIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where year not equals to DEFAULT_YEAR
        defaultMovieShouldNotBeFound("year.notEquals=" + DEFAULT_YEAR);

        // Get all the movieList where year not equals to UPDATED_YEAR
        defaultMovieShouldBeFound("year.notEquals=" + UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void getAllMoviesByYearIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where year in DEFAULT_YEAR or UPDATED_YEAR
        defaultMovieShouldBeFound("year.in=" + DEFAULT_YEAR + "," + UPDATED_YEAR);

        // Get all the movieList where year equals to UPDATED_YEAR
        defaultMovieShouldNotBeFound("year.in=" + UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void getAllMoviesByYearIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where year is not null
        defaultMovieShouldBeFound("year.specified=true");

        // Get all the movieList where year is null
        defaultMovieShouldNotBeFound("year.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoviesByYearIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where year is greater than or equal to DEFAULT_YEAR
        defaultMovieShouldBeFound("year.greaterThanOrEqual=" + DEFAULT_YEAR);

        // Get all the movieList where year is greater than or equal to UPDATED_YEAR
        defaultMovieShouldNotBeFound("year.greaterThanOrEqual=" + UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void getAllMoviesByYearIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where year is less than or equal to DEFAULT_YEAR
        defaultMovieShouldBeFound("year.lessThanOrEqual=" + DEFAULT_YEAR);

        // Get all the movieList where year is less than or equal to SMALLER_YEAR
        defaultMovieShouldNotBeFound("year.lessThanOrEqual=" + SMALLER_YEAR);
    }

    @Test
    @Transactional
    public void getAllMoviesByYearIsLessThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where year is less than DEFAULT_YEAR
        defaultMovieShouldNotBeFound("year.lessThan=" + DEFAULT_YEAR);

        // Get all the movieList where year is less than UPDATED_YEAR
        defaultMovieShouldBeFound("year.lessThan=" + UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void getAllMoviesByYearIsGreaterThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where year is greater than DEFAULT_YEAR
        defaultMovieShouldNotBeFound("year.greaterThan=" + DEFAULT_YEAR);

        // Get all the movieList where year is greater than SMALLER_YEAR
        defaultMovieShouldBeFound("year.greaterThan=" + SMALLER_YEAR);
    }


    @Test
    @Transactional
    public void getAllMoviesByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where rate equals to DEFAULT_RATE
        defaultMovieShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the movieList where rate equals to UPDATED_RATE
        defaultMovieShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByRateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where rate not equals to DEFAULT_RATE
        defaultMovieShouldNotBeFound("rate.notEquals=" + DEFAULT_RATE);

        // Get all the movieList where rate not equals to UPDATED_RATE
        defaultMovieShouldBeFound("rate.notEquals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByRateIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultMovieShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the movieList where rate equals to UPDATED_RATE
        defaultMovieShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where rate is not null
        defaultMovieShouldBeFound("rate.specified=true");

        // Get all the movieList where rate is null
        defaultMovieShouldNotBeFound("rate.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByRateContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where rate contains DEFAULT_RATE
        defaultMovieShouldBeFound("rate.contains=" + DEFAULT_RATE);

        // Get all the movieList where rate contains UPDATED_RATE
        defaultMovieShouldNotBeFound("rate.contains=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByRateNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where rate does not contain DEFAULT_RATE
        defaultMovieShouldNotBeFound("rate.doesNotContain=" + DEFAULT_RATE);

        // Get all the movieList where rate does not contain UPDATED_RATE
        defaultMovieShouldBeFound("rate.doesNotContain=" + UPDATED_RATE);
    }


    @Test
    @Transactional
    public void getAllMoviesByReleaseDateIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where releaseDate equals to DEFAULT_RELEASE_DATE
        defaultMovieShouldBeFound("releaseDate.equals=" + DEFAULT_RELEASE_DATE);

        // Get all the movieList where releaseDate equals to UPDATED_RELEASE_DATE
        defaultMovieShouldNotBeFound("releaseDate.equals=" + UPDATED_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByReleaseDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where releaseDate not equals to DEFAULT_RELEASE_DATE
        defaultMovieShouldNotBeFound("releaseDate.notEquals=" + DEFAULT_RELEASE_DATE);

        // Get all the movieList where releaseDate not equals to UPDATED_RELEASE_DATE
        defaultMovieShouldBeFound("releaseDate.notEquals=" + UPDATED_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByReleaseDateIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where releaseDate in DEFAULT_RELEASE_DATE or UPDATED_RELEASE_DATE
        defaultMovieShouldBeFound("releaseDate.in=" + DEFAULT_RELEASE_DATE + "," + UPDATED_RELEASE_DATE);

        // Get all the movieList where releaseDate equals to UPDATED_RELEASE_DATE
        defaultMovieShouldNotBeFound("releaseDate.in=" + UPDATED_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByReleaseDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where releaseDate is not null
        defaultMovieShouldBeFound("releaseDate.specified=true");

        // Get all the movieList where releaseDate is null
        defaultMovieShouldNotBeFound("releaseDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoviesByReleaseDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where releaseDate is greater than or equal to DEFAULT_RELEASE_DATE
        defaultMovieShouldBeFound("releaseDate.greaterThanOrEqual=" + DEFAULT_RELEASE_DATE);

        // Get all the movieList where releaseDate is greater than or equal to UPDATED_RELEASE_DATE
        defaultMovieShouldNotBeFound("releaseDate.greaterThanOrEqual=" + UPDATED_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByReleaseDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where releaseDate is less than or equal to DEFAULT_RELEASE_DATE
        defaultMovieShouldBeFound("releaseDate.lessThanOrEqual=" + DEFAULT_RELEASE_DATE);

        // Get all the movieList where releaseDate is less than or equal to SMALLER_RELEASE_DATE
        defaultMovieShouldNotBeFound("releaseDate.lessThanOrEqual=" + SMALLER_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByReleaseDateIsLessThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where releaseDate is less than DEFAULT_RELEASE_DATE
        defaultMovieShouldNotBeFound("releaseDate.lessThan=" + DEFAULT_RELEASE_DATE);

        // Get all the movieList where releaseDate is less than UPDATED_RELEASE_DATE
        defaultMovieShouldBeFound("releaseDate.lessThan=" + UPDATED_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByReleaseDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where releaseDate is greater than DEFAULT_RELEASE_DATE
        defaultMovieShouldNotBeFound("releaseDate.greaterThan=" + DEFAULT_RELEASE_DATE);

        // Get all the movieList where releaseDate is greater than SMALLER_RELEASE_DATE
        defaultMovieShouldBeFound("releaseDate.greaterThan=" + SMALLER_RELEASE_DATE);
    }


    @Test
    @Transactional
    public void getAllMoviesByRuntimeIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where runtime equals to DEFAULT_RUNTIME
        defaultMovieShouldBeFound("runtime.equals=" + DEFAULT_RUNTIME);

        // Get all the movieList where runtime equals to UPDATED_RUNTIME
        defaultMovieShouldNotBeFound("runtime.equals=" + UPDATED_RUNTIME);
    }

    @Test
    @Transactional
    public void getAllMoviesByRuntimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where runtime not equals to DEFAULT_RUNTIME
        defaultMovieShouldNotBeFound("runtime.notEquals=" + DEFAULT_RUNTIME);

        // Get all the movieList where runtime not equals to UPDATED_RUNTIME
        defaultMovieShouldBeFound("runtime.notEquals=" + UPDATED_RUNTIME);
    }

    @Test
    @Transactional
    public void getAllMoviesByRuntimeIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where runtime in DEFAULT_RUNTIME or UPDATED_RUNTIME
        defaultMovieShouldBeFound("runtime.in=" + DEFAULT_RUNTIME + "," + UPDATED_RUNTIME);

        // Get all the movieList where runtime equals to UPDATED_RUNTIME
        defaultMovieShouldNotBeFound("runtime.in=" + UPDATED_RUNTIME);
    }

    @Test
    @Transactional
    public void getAllMoviesByRuntimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where runtime is not null
        defaultMovieShouldBeFound("runtime.specified=true");

        // Get all the movieList where runtime is null
        defaultMovieShouldNotBeFound("runtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoviesByRuntimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where runtime is greater than or equal to DEFAULT_RUNTIME
        defaultMovieShouldBeFound("runtime.greaterThanOrEqual=" + DEFAULT_RUNTIME);

        // Get all the movieList where runtime is greater than or equal to UPDATED_RUNTIME
        defaultMovieShouldNotBeFound("runtime.greaterThanOrEqual=" + UPDATED_RUNTIME);
    }

    @Test
    @Transactional
    public void getAllMoviesByRuntimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where runtime is less than or equal to DEFAULT_RUNTIME
        defaultMovieShouldBeFound("runtime.lessThanOrEqual=" + DEFAULT_RUNTIME);

        // Get all the movieList where runtime is less than or equal to SMALLER_RUNTIME
        defaultMovieShouldNotBeFound("runtime.lessThanOrEqual=" + SMALLER_RUNTIME);
    }

    @Test
    @Transactional
    public void getAllMoviesByRuntimeIsLessThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where runtime is less than DEFAULT_RUNTIME
        defaultMovieShouldNotBeFound("runtime.lessThan=" + DEFAULT_RUNTIME);

        // Get all the movieList where runtime is less than UPDATED_RUNTIME
        defaultMovieShouldBeFound("runtime.lessThan=" + UPDATED_RUNTIME);
    }

    @Test
    @Transactional
    public void getAllMoviesByRuntimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where runtime is greater than DEFAULT_RUNTIME
        defaultMovieShouldNotBeFound("runtime.greaterThan=" + DEFAULT_RUNTIME);

        // Get all the movieList where runtime is greater than SMALLER_RUNTIME
        defaultMovieShouldBeFound("runtime.greaterThan=" + SMALLER_RUNTIME);
    }


    @Test
    @Transactional
    public void getAllMoviesByGenreIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where genre equals to DEFAULT_GENRE
        defaultMovieShouldBeFound("genre.equals=" + DEFAULT_GENRE);

        // Get all the movieList where genre equals to UPDATED_GENRE
        defaultMovieShouldNotBeFound("genre.equals=" + UPDATED_GENRE);
    }

    @Test
    @Transactional
    public void getAllMoviesByGenreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where genre not equals to DEFAULT_GENRE
        defaultMovieShouldNotBeFound("genre.notEquals=" + DEFAULT_GENRE);

        // Get all the movieList where genre not equals to UPDATED_GENRE
        defaultMovieShouldBeFound("genre.notEquals=" + UPDATED_GENRE);
    }

    @Test
    @Transactional
    public void getAllMoviesByGenreIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where genre in DEFAULT_GENRE or UPDATED_GENRE
        defaultMovieShouldBeFound("genre.in=" + DEFAULT_GENRE + "," + UPDATED_GENRE);

        // Get all the movieList where genre equals to UPDATED_GENRE
        defaultMovieShouldNotBeFound("genre.in=" + UPDATED_GENRE);
    }

    @Test
    @Transactional
    public void getAllMoviesByGenreIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where genre is not null
        defaultMovieShouldBeFound("genre.specified=true");

        // Get all the movieList where genre is null
        defaultMovieShouldNotBeFound("genre.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByGenreContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where genre contains DEFAULT_GENRE
        defaultMovieShouldBeFound("genre.contains=" + DEFAULT_GENRE);

        // Get all the movieList where genre contains UPDATED_GENRE
        defaultMovieShouldNotBeFound("genre.contains=" + UPDATED_GENRE);
    }

    @Test
    @Transactional
    public void getAllMoviesByGenreNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where genre does not contain DEFAULT_GENRE
        defaultMovieShouldNotBeFound("genre.doesNotContain=" + DEFAULT_GENRE);

        // Get all the movieList where genre does not contain UPDATED_GENRE
        defaultMovieShouldBeFound("genre.doesNotContain=" + UPDATED_GENRE);
    }


    @Test
    @Transactional
    public void getAllMoviesByDirectorIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where director equals to DEFAULT_DIRECTOR
        defaultMovieShouldBeFound("director.equals=" + DEFAULT_DIRECTOR);

        // Get all the movieList where director equals to UPDATED_DIRECTOR
        defaultMovieShouldNotBeFound("director.equals=" + UPDATED_DIRECTOR);
    }

    @Test
    @Transactional
    public void getAllMoviesByDirectorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where director not equals to DEFAULT_DIRECTOR
        defaultMovieShouldNotBeFound("director.notEquals=" + DEFAULT_DIRECTOR);

        // Get all the movieList where director not equals to UPDATED_DIRECTOR
        defaultMovieShouldBeFound("director.notEquals=" + UPDATED_DIRECTOR);
    }

    @Test
    @Transactional
    public void getAllMoviesByDirectorIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where director in DEFAULT_DIRECTOR or UPDATED_DIRECTOR
        defaultMovieShouldBeFound("director.in=" + DEFAULT_DIRECTOR + "," + UPDATED_DIRECTOR);

        // Get all the movieList where director equals to UPDATED_DIRECTOR
        defaultMovieShouldNotBeFound("director.in=" + UPDATED_DIRECTOR);
    }

    @Test
    @Transactional
    public void getAllMoviesByDirectorIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where director is not null
        defaultMovieShouldBeFound("director.specified=true");

        // Get all the movieList where director is null
        defaultMovieShouldNotBeFound("director.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByDirectorContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where director contains DEFAULT_DIRECTOR
        defaultMovieShouldBeFound("director.contains=" + DEFAULT_DIRECTOR);

        // Get all the movieList where director contains UPDATED_DIRECTOR
        defaultMovieShouldNotBeFound("director.contains=" + UPDATED_DIRECTOR);
    }

    @Test
    @Transactional
    public void getAllMoviesByDirectorNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where director does not contain DEFAULT_DIRECTOR
        defaultMovieShouldNotBeFound("director.doesNotContain=" + DEFAULT_DIRECTOR);

        // Get all the movieList where director does not contain UPDATED_DIRECTOR
        defaultMovieShouldBeFound("director.doesNotContain=" + UPDATED_DIRECTOR);
    }


    @Test
    @Transactional
    public void getAllMoviesByWritersIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where writers equals to DEFAULT_WRITERS
        defaultMovieShouldBeFound("writers.equals=" + DEFAULT_WRITERS);

        // Get all the movieList where writers equals to UPDATED_WRITERS
        defaultMovieShouldNotBeFound("writers.equals=" + UPDATED_WRITERS);
    }

    @Test
    @Transactional
    public void getAllMoviesByWritersIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where writers not equals to DEFAULT_WRITERS
        defaultMovieShouldNotBeFound("writers.notEquals=" + DEFAULT_WRITERS);

        // Get all the movieList where writers not equals to UPDATED_WRITERS
        defaultMovieShouldBeFound("writers.notEquals=" + UPDATED_WRITERS);
    }

    @Test
    @Transactional
    public void getAllMoviesByWritersIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where writers in DEFAULT_WRITERS or UPDATED_WRITERS
        defaultMovieShouldBeFound("writers.in=" + DEFAULT_WRITERS + "," + UPDATED_WRITERS);

        // Get all the movieList where writers equals to UPDATED_WRITERS
        defaultMovieShouldNotBeFound("writers.in=" + UPDATED_WRITERS);
    }

    @Test
    @Transactional
    public void getAllMoviesByWritersIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where writers is not null
        defaultMovieShouldBeFound("writers.specified=true");

        // Get all the movieList where writers is null
        defaultMovieShouldNotBeFound("writers.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByWritersContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where writers contains DEFAULT_WRITERS
        defaultMovieShouldBeFound("writers.contains=" + DEFAULT_WRITERS);

        // Get all the movieList where writers contains UPDATED_WRITERS
        defaultMovieShouldNotBeFound("writers.contains=" + UPDATED_WRITERS);
    }

    @Test
    @Transactional
    public void getAllMoviesByWritersNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where writers does not contain DEFAULT_WRITERS
        defaultMovieShouldNotBeFound("writers.doesNotContain=" + DEFAULT_WRITERS);

        // Get all the movieList where writers does not contain UPDATED_WRITERS
        defaultMovieShouldBeFound("writers.doesNotContain=" + UPDATED_WRITERS);
    }


    @Test
    @Transactional
    public void getAllMoviesByActorsIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where actors equals to DEFAULT_ACTORS
        defaultMovieShouldBeFound("actors.equals=" + DEFAULT_ACTORS);

        // Get all the movieList where actors equals to UPDATED_ACTORS
        defaultMovieShouldNotBeFound("actors.equals=" + UPDATED_ACTORS);
    }

    @Test
    @Transactional
    public void getAllMoviesByActorsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where actors not equals to DEFAULT_ACTORS
        defaultMovieShouldNotBeFound("actors.notEquals=" + DEFAULT_ACTORS);

        // Get all the movieList where actors not equals to UPDATED_ACTORS
        defaultMovieShouldBeFound("actors.notEquals=" + UPDATED_ACTORS);
    }

    @Test
    @Transactional
    public void getAllMoviesByActorsIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where actors in DEFAULT_ACTORS or UPDATED_ACTORS
        defaultMovieShouldBeFound("actors.in=" + DEFAULT_ACTORS + "," + UPDATED_ACTORS);

        // Get all the movieList where actors equals to UPDATED_ACTORS
        defaultMovieShouldNotBeFound("actors.in=" + UPDATED_ACTORS);
    }

    @Test
    @Transactional
    public void getAllMoviesByActorsIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where actors is not null
        defaultMovieShouldBeFound("actors.specified=true");

        // Get all the movieList where actors is null
        defaultMovieShouldNotBeFound("actors.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByActorsContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where actors contains DEFAULT_ACTORS
        defaultMovieShouldBeFound("actors.contains=" + DEFAULT_ACTORS);

        // Get all the movieList where actors contains UPDATED_ACTORS
        defaultMovieShouldNotBeFound("actors.contains=" + UPDATED_ACTORS);
    }

    @Test
    @Transactional
    public void getAllMoviesByActorsNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where actors does not contain DEFAULT_ACTORS
        defaultMovieShouldNotBeFound("actors.doesNotContain=" + DEFAULT_ACTORS);

        // Get all the movieList where actors does not contain UPDATED_ACTORS
        defaultMovieShouldBeFound("actors.doesNotContain=" + UPDATED_ACTORS);
    }


    @Test
    @Transactional
    public void getAllMoviesByPlotIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where plot equals to DEFAULT_PLOT
        defaultMovieShouldBeFound("plot.equals=" + DEFAULT_PLOT);

        // Get all the movieList where plot equals to UPDATED_PLOT
        defaultMovieShouldNotBeFound("plot.equals=" + UPDATED_PLOT);
    }

    @Test
    @Transactional
    public void getAllMoviesByPlotIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where plot not equals to DEFAULT_PLOT
        defaultMovieShouldNotBeFound("plot.notEquals=" + DEFAULT_PLOT);

        // Get all the movieList where plot not equals to UPDATED_PLOT
        defaultMovieShouldBeFound("plot.notEquals=" + UPDATED_PLOT);
    }

    @Test
    @Transactional
    public void getAllMoviesByPlotIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where plot in DEFAULT_PLOT or UPDATED_PLOT
        defaultMovieShouldBeFound("plot.in=" + DEFAULT_PLOT + "," + UPDATED_PLOT);

        // Get all the movieList where plot equals to UPDATED_PLOT
        defaultMovieShouldNotBeFound("plot.in=" + UPDATED_PLOT);
    }

    @Test
    @Transactional
    public void getAllMoviesByPlotIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where plot is not null
        defaultMovieShouldBeFound("plot.specified=true");

        // Get all the movieList where plot is null
        defaultMovieShouldNotBeFound("plot.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByPlotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where plot contains DEFAULT_PLOT
        defaultMovieShouldBeFound("plot.contains=" + DEFAULT_PLOT);

        // Get all the movieList where plot contains UPDATED_PLOT
        defaultMovieShouldNotBeFound("plot.contains=" + UPDATED_PLOT);
    }

    @Test
    @Transactional
    public void getAllMoviesByPlotNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where plot does not contain DEFAULT_PLOT
        defaultMovieShouldNotBeFound("plot.doesNotContain=" + DEFAULT_PLOT);

        // Get all the movieList where plot does not contain UPDATED_PLOT
        defaultMovieShouldBeFound("plot.doesNotContain=" + UPDATED_PLOT);
    }


    @Test
    @Transactional
    public void getAllMoviesByLanguageIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where language equals to DEFAULT_LANGUAGE
        defaultMovieShouldBeFound("language.equals=" + DEFAULT_LANGUAGE);

        // Get all the movieList where language equals to UPDATED_LANGUAGE
        defaultMovieShouldNotBeFound("language.equals=" + UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void getAllMoviesByLanguageIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where language not equals to DEFAULT_LANGUAGE
        defaultMovieShouldNotBeFound("language.notEquals=" + DEFAULT_LANGUAGE);

        // Get all the movieList where language not equals to UPDATED_LANGUAGE
        defaultMovieShouldBeFound("language.notEquals=" + UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void getAllMoviesByLanguageIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where language in DEFAULT_LANGUAGE or UPDATED_LANGUAGE
        defaultMovieShouldBeFound("language.in=" + DEFAULT_LANGUAGE + "," + UPDATED_LANGUAGE);

        // Get all the movieList where language equals to UPDATED_LANGUAGE
        defaultMovieShouldNotBeFound("language.in=" + UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void getAllMoviesByLanguageIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where language is not null
        defaultMovieShouldBeFound("language.specified=true");

        // Get all the movieList where language is null
        defaultMovieShouldNotBeFound("language.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByLanguageContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where language contains DEFAULT_LANGUAGE
        defaultMovieShouldBeFound("language.contains=" + DEFAULT_LANGUAGE);

        // Get all the movieList where language contains UPDATED_LANGUAGE
        defaultMovieShouldNotBeFound("language.contains=" + UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void getAllMoviesByLanguageNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where language does not contain DEFAULT_LANGUAGE
        defaultMovieShouldNotBeFound("language.doesNotContain=" + DEFAULT_LANGUAGE);

        // Get all the movieList where language does not contain UPDATED_LANGUAGE
        defaultMovieShouldBeFound("language.doesNotContain=" + UPDATED_LANGUAGE);
    }


    @Test
    @Transactional
    public void getAllMoviesByCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where country equals to DEFAULT_COUNTRY
        defaultMovieShouldBeFound("country.equals=" + DEFAULT_COUNTRY);

        // Get all the movieList where country equals to UPDATED_COUNTRY
        defaultMovieShouldNotBeFound("country.equals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllMoviesByCountryIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where country not equals to DEFAULT_COUNTRY
        defaultMovieShouldNotBeFound("country.notEquals=" + DEFAULT_COUNTRY);

        // Get all the movieList where country not equals to UPDATED_COUNTRY
        defaultMovieShouldBeFound("country.notEquals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllMoviesByCountryIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where country in DEFAULT_COUNTRY or UPDATED_COUNTRY
        defaultMovieShouldBeFound("country.in=" + DEFAULT_COUNTRY + "," + UPDATED_COUNTRY);

        // Get all the movieList where country equals to UPDATED_COUNTRY
        defaultMovieShouldNotBeFound("country.in=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllMoviesByCountryIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where country is not null
        defaultMovieShouldBeFound("country.specified=true");

        // Get all the movieList where country is null
        defaultMovieShouldNotBeFound("country.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByCountryContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where country contains DEFAULT_COUNTRY
        defaultMovieShouldBeFound("country.contains=" + DEFAULT_COUNTRY);

        // Get all the movieList where country contains UPDATED_COUNTRY
        defaultMovieShouldNotBeFound("country.contains=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllMoviesByCountryNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where country does not contain DEFAULT_COUNTRY
        defaultMovieShouldNotBeFound("country.doesNotContain=" + DEFAULT_COUNTRY);

        // Get all the movieList where country does not contain UPDATED_COUNTRY
        defaultMovieShouldBeFound("country.doesNotContain=" + UPDATED_COUNTRY);
    }


    @Test
    @Transactional
    public void getAllMoviesByAwardsIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where awards equals to DEFAULT_AWARDS
        defaultMovieShouldBeFound("awards.equals=" + DEFAULT_AWARDS);

        // Get all the movieList where awards equals to UPDATED_AWARDS
        defaultMovieShouldNotBeFound("awards.equals=" + UPDATED_AWARDS);
    }

    @Test
    @Transactional
    public void getAllMoviesByAwardsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where awards not equals to DEFAULT_AWARDS
        defaultMovieShouldNotBeFound("awards.notEquals=" + DEFAULT_AWARDS);

        // Get all the movieList where awards not equals to UPDATED_AWARDS
        defaultMovieShouldBeFound("awards.notEquals=" + UPDATED_AWARDS);
    }

    @Test
    @Transactional
    public void getAllMoviesByAwardsIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where awards in DEFAULT_AWARDS or UPDATED_AWARDS
        defaultMovieShouldBeFound("awards.in=" + DEFAULT_AWARDS + "," + UPDATED_AWARDS);

        // Get all the movieList where awards equals to UPDATED_AWARDS
        defaultMovieShouldNotBeFound("awards.in=" + UPDATED_AWARDS);
    }

    @Test
    @Transactional
    public void getAllMoviesByAwardsIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where awards is not null
        defaultMovieShouldBeFound("awards.specified=true");

        // Get all the movieList where awards is null
        defaultMovieShouldNotBeFound("awards.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByAwardsContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where awards contains DEFAULT_AWARDS
        defaultMovieShouldBeFound("awards.contains=" + DEFAULT_AWARDS);

        // Get all the movieList where awards contains UPDATED_AWARDS
        defaultMovieShouldNotBeFound("awards.contains=" + UPDATED_AWARDS);
    }

    @Test
    @Transactional
    public void getAllMoviesByAwardsNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where awards does not contain DEFAULT_AWARDS
        defaultMovieShouldNotBeFound("awards.doesNotContain=" + DEFAULT_AWARDS);

        // Get all the movieList where awards does not contain UPDATED_AWARDS
        defaultMovieShouldBeFound("awards.doesNotContain=" + UPDATED_AWARDS);
    }


    @Test
    @Transactional
    public void getAllMoviesByPosterLinkIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where posterLink equals to DEFAULT_POSTER_LINK
        defaultMovieShouldBeFound("posterLink.equals=" + DEFAULT_POSTER_LINK);

        // Get all the movieList where posterLink equals to UPDATED_POSTER_LINK
        defaultMovieShouldNotBeFound("posterLink.equals=" + UPDATED_POSTER_LINK);
    }

    @Test
    @Transactional
    public void getAllMoviesByPosterLinkIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where posterLink not equals to DEFAULT_POSTER_LINK
        defaultMovieShouldNotBeFound("posterLink.notEquals=" + DEFAULT_POSTER_LINK);

        // Get all the movieList where posterLink not equals to UPDATED_POSTER_LINK
        defaultMovieShouldBeFound("posterLink.notEquals=" + UPDATED_POSTER_LINK);
    }

    @Test
    @Transactional
    public void getAllMoviesByPosterLinkIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where posterLink in DEFAULT_POSTER_LINK or UPDATED_POSTER_LINK
        defaultMovieShouldBeFound("posterLink.in=" + DEFAULT_POSTER_LINK + "," + UPDATED_POSTER_LINK);

        // Get all the movieList where posterLink equals to UPDATED_POSTER_LINK
        defaultMovieShouldNotBeFound("posterLink.in=" + UPDATED_POSTER_LINK);
    }

    @Test
    @Transactional
    public void getAllMoviesByPosterLinkIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where posterLink is not null
        defaultMovieShouldBeFound("posterLink.specified=true");

        // Get all the movieList where posterLink is null
        defaultMovieShouldNotBeFound("posterLink.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByPosterLinkContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where posterLink contains DEFAULT_POSTER_LINK
        defaultMovieShouldBeFound("posterLink.contains=" + DEFAULT_POSTER_LINK);

        // Get all the movieList where posterLink contains UPDATED_POSTER_LINK
        defaultMovieShouldNotBeFound("posterLink.contains=" + UPDATED_POSTER_LINK);
    }

    @Test
    @Transactional
    public void getAllMoviesByPosterLinkNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where posterLink does not contain DEFAULT_POSTER_LINK
        defaultMovieShouldNotBeFound("posterLink.doesNotContain=" + DEFAULT_POSTER_LINK);

        // Get all the movieList where posterLink does not contain UPDATED_POSTER_LINK
        defaultMovieShouldBeFound("posterLink.doesNotContain=" + UPDATED_POSTER_LINK);
    }


    @Test
    @Transactional
    public void getAllMoviesByRatingsIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where ratings equals to DEFAULT_RATINGS
        defaultMovieShouldBeFound("ratings.equals=" + DEFAULT_RATINGS);

        // Get all the movieList where ratings equals to UPDATED_RATINGS
        defaultMovieShouldNotBeFound("ratings.equals=" + UPDATED_RATINGS);
    }

    @Test
    @Transactional
    public void getAllMoviesByRatingsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where ratings not equals to DEFAULT_RATINGS
        defaultMovieShouldNotBeFound("ratings.notEquals=" + DEFAULT_RATINGS);

        // Get all the movieList where ratings not equals to UPDATED_RATINGS
        defaultMovieShouldBeFound("ratings.notEquals=" + UPDATED_RATINGS);
    }

    @Test
    @Transactional
    public void getAllMoviesByRatingsIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where ratings in DEFAULT_RATINGS or UPDATED_RATINGS
        defaultMovieShouldBeFound("ratings.in=" + DEFAULT_RATINGS + "," + UPDATED_RATINGS);

        // Get all the movieList where ratings equals to UPDATED_RATINGS
        defaultMovieShouldNotBeFound("ratings.in=" + UPDATED_RATINGS);
    }

    @Test
    @Transactional
    public void getAllMoviesByRatingsIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where ratings is not null
        defaultMovieShouldBeFound("ratings.specified=true");

        // Get all the movieList where ratings is null
        defaultMovieShouldNotBeFound("ratings.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByRatingsContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where ratings contains DEFAULT_RATINGS
        defaultMovieShouldBeFound("ratings.contains=" + DEFAULT_RATINGS);

        // Get all the movieList where ratings contains UPDATED_RATINGS
        defaultMovieShouldNotBeFound("ratings.contains=" + UPDATED_RATINGS);
    }

    @Test
    @Transactional
    public void getAllMoviesByRatingsNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where ratings does not contain DEFAULT_RATINGS
        defaultMovieShouldNotBeFound("ratings.doesNotContain=" + DEFAULT_RATINGS);

        // Get all the movieList where ratings does not contain UPDATED_RATINGS
        defaultMovieShouldBeFound("ratings.doesNotContain=" + UPDATED_RATINGS);
    }


    @Test
    @Transactional
    public void getAllMoviesByMetascoreIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where metascore equals to DEFAULT_METASCORE
        defaultMovieShouldBeFound("metascore.equals=" + DEFAULT_METASCORE);

        // Get all the movieList where metascore equals to UPDATED_METASCORE
        defaultMovieShouldNotBeFound("metascore.equals=" + UPDATED_METASCORE);
    }

    @Test
    @Transactional
    public void getAllMoviesByMetascoreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where metascore not equals to DEFAULT_METASCORE
        defaultMovieShouldNotBeFound("metascore.notEquals=" + DEFAULT_METASCORE);

        // Get all the movieList where metascore not equals to UPDATED_METASCORE
        defaultMovieShouldBeFound("metascore.notEquals=" + UPDATED_METASCORE);
    }

    @Test
    @Transactional
    public void getAllMoviesByMetascoreIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where metascore in DEFAULT_METASCORE or UPDATED_METASCORE
        defaultMovieShouldBeFound("metascore.in=" + DEFAULT_METASCORE + "," + UPDATED_METASCORE);

        // Get all the movieList where metascore equals to UPDATED_METASCORE
        defaultMovieShouldNotBeFound("metascore.in=" + UPDATED_METASCORE);
    }

    @Test
    @Transactional
    public void getAllMoviesByMetascoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where metascore is not null
        defaultMovieShouldBeFound("metascore.specified=true");

        // Get all the movieList where metascore is null
        defaultMovieShouldNotBeFound("metascore.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoviesByMetascoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where metascore is greater than or equal to DEFAULT_METASCORE
        defaultMovieShouldBeFound("metascore.greaterThanOrEqual=" + DEFAULT_METASCORE);

        // Get all the movieList where metascore is greater than or equal to UPDATED_METASCORE
        defaultMovieShouldNotBeFound("metascore.greaterThanOrEqual=" + UPDATED_METASCORE);
    }

    @Test
    @Transactional
    public void getAllMoviesByMetascoreIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where metascore is less than or equal to DEFAULT_METASCORE
        defaultMovieShouldBeFound("metascore.lessThanOrEqual=" + DEFAULT_METASCORE);

        // Get all the movieList where metascore is less than or equal to SMALLER_METASCORE
        defaultMovieShouldNotBeFound("metascore.lessThanOrEqual=" + SMALLER_METASCORE);
    }

    @Test
    @Transactional
    public void getAllMoviesByMetascoreIsLessThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where metascore is less than DEFAULT_METASCORE
        defaultMovieShouldNotBeFound("metascore.lessThan=" + DEFAULT_METASCORE);

        // Get all the movieList where metascore is less than UPDATED_METASCORE
        defaultMovieShouldBeFound("metascore.lessThan=" + UPDATED_METASCORE);
    }

    @Test
    @Transactional
    public void getAllMoviesByMetascoreIsGreaterThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where metascore is greater than DEFAULT_METASCORE
        defaultMovieShouldNotBeFound("metascore.greaterThan=" + DEFAULT_METASCORE);

        // Get all the movieList where metascore is greater than SMALLER_METASCORE
        defaultMovieShouldBeFound("metascore.greaterThan=" + SMALLER_METASCORE);
    }


    @Test
    @Transactional
    public void getAllMoviesByImdbRatingIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbRating equals to DEFAULT_IMDB_RATING
        defaultMovieShouldBeFound("imdbRating.equals=" + DEFAULT_IMDB_RATING);

        // Get all the movieList where imdbRating equals to UPDATED_IMDB_RATING
        defaultMovieShouldNotBeFound("imdbRating.equals=" + UPDATED_IMDB_RATING);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbRatingIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbRating not equals to DEFAULT_IMDB_RATING
        defaultMovieShouldNotBeFound("imdbRating.notEquals=" + DEFAULT_IMDB_RATING);

        // Get all the movieList where imdbRating not equals to UPDATED_IMDB_RATING
        defaultMovieShouldBeFound("imdbRating.notEquals=" + UPDATED_IMDB_RATING);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbRatingIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbRating in DEFAULT_IMDB_RATING or UPDATED_IMDB_RATING
        defaultMovieShouldBeFound("imdbRating.in=" + DEFAULT_IMDB_RATING + "," + UPDATED_IMDB_RATING);

        // Get all the movieList where imdbRating equals to UPDATED_IMDB_RATING
        defaultMovieShouldNotBeFound("imdbRating.in=" + UPDATED_IMDB_RATING);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbRatingIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbRating is not null
        defaultMovieShouldBeFound("imdbRating.specified=true");

        // Get all the movieList where imdbRating is null
        defaultMovieShouldNotBeFound("imdbRating.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbRatingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbRating is greater than or equal to DEFAULT_IMDB_RATING
        defaultMovieShouldBeFound("imdbRating.greaterThanOrEqual=" + DEFAULT_IMDB_RATING);

        // Get all the movieList where imdbRating is greater than or equal to UPDATED_IMDB_RATING
        defaultMovieShouldNotBeFound("imdbRating.greaterThanOrEqual=" + UPDATED_IMDB_RATING);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbRatingIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbRating is less than or equal to DEFAULT_IMDB_RATING
        defaultMovieShouldBeFound("imdbRating.lessThanOrEqual=" + DEFAULT_IMDB_RATING);

        // Get all the movieList where imdbRating is less than or equal to SMALLER_IMDB_RATING
        defaultMovieShouldNotBeFound("imdbRating.lessThanOrEqual=" + SMALLER_IMDB_RATING);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbRatingIsLessThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbRating is less than DEFAULT_IMDB_RATING
        defaultMovieShouldNotBeFound("imdbRating.lessThan=" + DEFAULT_IMDB_RATING);

        // Get all the movieList where imdbRating is less than UPDATED_IMDB_RATING
        defaultMovieShouldBeFound("imdbRating.lessThan=" + UPDATED_IMDB_RATING);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbRatingIsGreaterThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbRating is greater than DEFAULT_IMDB_RATING
        defaultMovieShouldNotBeFound("imdbRating.greaterThan=" + DEFAULT_IMDB_RATING);

        // Get all the movieList where imdbRating is greater than SMALLER_IMDB_RATING
        defaultMovieShouldBeFound("imdbRating.greaterThan=" + SMALLER_IMDB_RATING);
    }


    @Test
    @Transactional
    public void getAllMoviesByImdbVotesIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbVotes equals to DEFAULT_IMDB_VOTES
        defaultMovieShouldBeFound("imdbVotes.equals=" + DEFAULT_IMDB_VOTES);

        // Get all the movieList where imdbVotes equals to UPDATED_IMDB_VOTES
        defaultMovieShouldNotBeFound("imdbVotes.equals=" + UPDATED_IMDB_VOTES);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbVotesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbVotes not equals to DEFAULT_IMDB_VOTES
        defaultMovieShouldNotBeFound("imdbVotes.notEquals=" + DEFAULT_IMDB_VOTES);

        // Get all the movieList where imdbVotes not equals to UPDATED_IMDB_VOTES
        defaultMovieShouldBeFound("imdbVotes.notEquals=" + UPDATED_IMDB_VOTES);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbVotesIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbVotes in DEFAULT_IMDB_VOTES or UPDATED_IMDB_VOTES
        defaultMovieShouldBeFound("imdbVotes.in=" + DEFAULT_IMDB_VOTES + "," + UPDATED_IMDB_VOTES);

        // Get all the movieList where imdbVotes equals to UPDATED_IMDB_VOTES
        defaultMovieShouldNotBeFound("imdbVotes.in=" + UPDATED_IMDB_VOTES);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbVotesIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbVotes is not null
        defaultMovieShouldBeFound("imdbVotes.specified=true");

        // Get all the movieList where imdbVotes is null
        defaultMovieShouldNotBeFound("imdbVotes.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbVotesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbVotes is greater than or equal to DEFAULT_IMDB_VOTES
        defaultMovieShouldBeFound("imdbVotes.greaterThanOrEqual=" + DEFAULT_IMDB_VOTES);

        // Get all the movieList where imdbVotes is greater than or equal to UPDATED_IMDB_VOTES
        defaultMovieShouldNotBeFound("imdbVotes.greaterThanOrEqual=" + UPDATED_IMDB_VOTES);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbVotesIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbVotes is less than or equal to DEFAULT_IMDB_VOTES
        defaultMovieShouldBeFound("imdbVotes.lessThanOrEqual=" + DEFAULT_IMDB_VOTES);

        // Get all the movieList where imdbVotes is less than or equal to SMALLER_IMDB_VOTES
        defaultMovieShouldNotBeFound("imdbVotes.lessThanOrEqual=" + SMALLER_IMDB_VOTES);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbVotesIsLessThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbVotes is less than DEFAULT_IMDB_VOTES
        defaultMovieShouldNotBeFound("imdbVotes.lessThan=" + DEFAULT_IMDB_VOTES);

        // Get all the movieList where imdbVotes is less than UPDATED_IMDB_VOTES
        defaultMovieShouldBeFound("imdbVotes.lessThan=" + UPDATED_IMDB_VOTES);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbVotesIsGreaterThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbVotes is greater than DEFAULT_IMDB_VOTES
        defaultMovieShouldNotBeFound("imdbVotes.greaterThan=" + DEFAULT_IMDB_VOTES);

        // Get all the movieList where imdbVotes is greater than SMALLER_IMDB_VOTES
        defaultMovieShouldBeFound("imdbVotes.greaterThan=" + SMALLER_IMDB_VOTES);
    }


    @Test
    @Transactional
    public void getAllMoviesByImdbIdIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbId equals to DEFAULT_IMDB_ID
        defaultMovieShouldBeFound("imdbId.equals=" + DEFAULT_IMDB_ID);

        // Get all the movieList where imdbId equals to UPDATED_IMDB_ID
        defaultMovieShouldNotBeFound("imdbId.equals=" + UPDATED_IMDB_ID);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbId not equals to DEFAULT_IMDB_ID
        defaultMovieShouldNotBeFound("imdbId.notEquals=" + DEFAULT_IMDB_ID);

        // Get all the movieList where imdbId not equals to UPDATED_IMDB_ID
        defaultMovieShouldBeFound("imdbId.notEquals=" + UPDATED_IMDB_ID);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbIdIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbId in DEFAULT_IMDB_ID or UPDATED_IMDB_ID
        defaultMovieShouldBeFound("imdbId.in=" + DEFAULT_IMDB_ID + "," + UPDATED_IMDB_ID);

        // Get all the movieList where imdbId equals to UPDATED_IMDB_ID
        defaultMovieShouldNotBeFound("imdbId.in=" + UPDATED_IMDB_ID);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbId is not null
        defaultMovieShouldBeFound("imdbId.specified=true");

        // Get all the movieList where imdbId is null
        defaultMovieShouldNotBeFound("imdbId.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByImdbIdContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbId contains DEFAULT_IMDB_ID
        defaultMovieShouldBeFound("imdbId.contains=" + DEFAULT_IMDB_ID);

        // Get all the movieList where imdbId contains UPDATED_IMDB_ID
        defaultMovieShouldNotBeFound("imdbId.contains=" + UPDATED_IMDB_ID);
    }

    @Test
    @Transactional
    public void getAllMoviesByImdbIdNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where imdbId does not contain DEFAULT_IMDB_ID
        defaultMovieShouldNotBeFound("imdbId.doesNotContain=" + DEFAULT_IMDB_ID);

        // Get all the movieList where imdbId does not contain UPDATED_IMDB_ID
        defaultMovieShouldBeFound("imdbId.doesNotContain=" + UPDATED_IMDB_ID);
    }


    @Test
    @Transactional
    public void getAllMoviesByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where type equals to DEFAULT_TYPE
        defaultMovieShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the movieList where type equals to UPDATED_TYPE
        defaultMovieShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMoviesByTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where type not equals to DEFAULT_TYPE
        defaultMovieShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

        // Get all the movieList where type not equals to UPDATED_TYPE
        defaultMovieShouldBeFound("type.notEquals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMoviesByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultMovieShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the movieList where type equals to UPDATED_TYPE
        defaultMovieShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMoviesByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where type is not null
        defaultMovieShouldBeFound("type.specified=true");

        // Get all the movieList where type is null
        defaultMovieShouldNotBeFound("type.specified=false");
    }
                @Test
    @Transactional
    public void getAllMoviesByTypeContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where type contains DEFAULT_TYPE
        defaultMovieShouldBeFound("type.contains=" + DEFAULT_TYPE);

        // Get all the movieList where type contains UPDATED_TYPE
        defaultMovieShouldNotBeFound("type.contains=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMoviesByTypeNotContainsSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where type does not contain DEFAULT_TYPE
        defaultMovieShouldNotBeFound("type.doesNotContain=" + DEFAULT_TYPE);

        // Get all the movieList where type does not contain UPDATED_TYPE
        defaultMovieShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
    }


    @Test
    @Transactional
    public void getAllMoviesByDvdReleaseDateIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where dvdReleaseDate equals to DEFAULT_DVD_RELEASE_DATE
        defaultMovieShouldBeFound("dvdReleaseDate.equals=" + DEFAULT_DVD_RELEASE_DATE);

        // Get all the movieList where dvdReleaseDate equals to UPDATED_DVD_RELEASE_DATE
        defaultMovieShouldNotBeFound("dvdReleaseDate.equals=" + UPDATED_DVD_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByDvdReleaseDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where dvdReleaseDate not equals to DEFAULT_DVD_RELEASE_DATE
        defaultMovieShouldNotBeFound("dvdReleaseDate.notEquals=" + DEFAULT_DVD_RELEASE_DATE);

        // Get all the movieList where dvdReleaseDate not equals to UPDATED_DVD_RELEASE_DATE
        defaultMovieShouldBeFound("dvdReleaseDate.notEquals=" + UPDATED_DVD_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByDvdReleaseDateIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where dvdReleaseDate in DEFAULT_DVD_RELEASE_DATE or UPDATED_DVD_RELEASE_DATE
        defaultMovieShouldBeFound("dvdReleaseDate.in=" + DEFAULT_DVD_RELEASE_DATE + "," + UPDATED_DVD_RELEASE_DATE);

        // Get all the movieList where dvdReleaseDate equals to UPDATED_DVD_RELEASE_DATE
        defaultMovieShouldNotBeFound("dvdReleaseDate.in=" + UPDATED_DVD_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByDvdReleaseDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where dvdReleaseDate is not null
        defaultMovieShouldBeFound("dvdReleaseDate.specified=true");

        // Get all the movieList where dvdReleaseDate is null
        defaultMovieShouldNotBeFound("dvdReleaseDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoviesByDvdReleaseDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where dvdReleaseDate is greater than or equal to DEFAULT_DVD_RELEASE_DATE
        defaultMovieShouldBeFound("dvdReleaseDate.greaterThanOrEqual=" + DEFAULT_DVD_RELEASE_DATE);

        // Get all the movieList where dvdReleaseDate is greater than or equal to UPDATED_DVD_RELEASE_DATE
        defaultMovieShouldNotBeFound("dvdReleaseDate.greaterThanOrEqual=" + UPDATED_DVD_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByDvdReleaseDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where dvdReleaseDate is less than or equal to DEFAULT_DVD_RELEASE_DATE
        defaultMovieShouldBeFound("dvdReleaseDate.lessThanOrEqual=" + DEFAULT_DVD_RELEASE_DATE);

        // Get all the movieList where dvdReleaseDate is less than or equal to SMALLER_DVD_RELEASE_DATE
        defaultMovieShouldNotBeFound("dvdReleaseDate.lessThanOrEqual=" + SMALLER_DVD_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByDvdReleaseDateIsLessThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where dvdReleaseDate is less than DEFAULT_DVD_RELEASE_DATE
        defaultMovieShouldNotBeFound("dvdReleaseDate.lessThan=" + DEFAULT_DVD_RELEASE_DATE);

        // Get all the movieList where dvdReleaseDate is less than UPDATED_DVD_RELEASE_DATE
        defaultMovieShouldBeFound("dvdReleaseDate.lessThan=" + UPDATED_DVD_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllMoviesByDvdReleaseDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where dvdReleaseDate is greater than DEFAULT_DVD_RELEASE_DATE
        defaultMovieShouldNotBeFound("dvdReleaseDate.greaterThan=" + DEFAULT_DVD_RELEASE_DATE);

        // Get all the movieList where dvdReleaseDate is greater than SMALLER_DVD_RELEASE_DATE
        defaultMovieShouldBeFound("dvdReleaseDate.greaterThan=" + SMALLER_DVD_RELEASE_DATE);
    }


    @Test
    @Transactional
    public void getAllMoviesByBoxOfficeIsEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where boxOffice equals to DEFAULT_BOX_OFFICE
        defaultMovieShouldBeFound("boxOffice.equals=" + DEFAULT_BOX_OFFICE);

        // Get all the movieList where boxOffice equals to UPDATED_BOX_OFFICE
        defaultMovieShouldNotBeFound("boxOffice.equals=" + UPDATED_BOX_OFFICE);
    }

    @Test
    @Transactional
    public void getAllMoviesByBoxOfficeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where boxOffice not equals to DEFAULT_BOX_OFFICE
        defaultMovieShouldNotBeFound("boxOffice.notEquals=" + DEFAULT_BOX_OFFICE);

        // Get all the movieList where boxOffice not equals to UPDATED_BOX_OFFICE
        defaultMovieShouldBeFound("boxOffice.notEquals=" + UPDATED_BOX_OFFICE);
    }

    @Test
    @Transactional
    public void getAllMoviesByBoxOfficeIsInShouldWork() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where boxOffice in DEFAULT_BOX_OFFICE or UPDATED_BOX_OFFICE
        defaultMovieShouldBeFound("boxOffice.in=" + DEFAULT_BOX_OFFICE + "," + UPDATED_BOX_OFFICE);

        // Get all the movieList where boxOffice equals to UPDATED_BOX_OFFICE
        defaultMovieShouldNotBeFound("boxOffice.in=" + UPDATED_BOX_OFFICE);
    }

    @Test
    @Transactional
    public void getAllMoviesByBoxOfficeIsNullOrNotNull() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where boxOffice is not null
        defaultMovieShouldBeFound("boxOffice.specified=true");

        // Get all the movieList where boxOffice is null
        defaultMovieShouldNotBeFound("boxOffice.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoviesByBoxOfficeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where boxOffice is greater than or equal to DEFAULT_BOX_OFFICE
        defaultMovieShouldBeFound("boxOffice.greaterThanOrEqual=" + DEFAULT_BOX_OFFICE);

        // Get all the movieList where boxOffice is greater than or equal to UPDATED_BOX_OFFICE
        defaultMovieShouldNotBeFound("boxOffice.greaterThanOrEqual=" + UPDATED_BOX_OFFICE);
    }

    @Test
    @Transactional
    public void getAllMoviesByBoxOfficeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where boxOffice is less than or equal to DEFAULT_BOX_OFFICE
        defaultMovieShouldBeFound("boxOffice.lessThanOrEqual=" + DEFAULT_BOX_OFFICE);

        // Get all the movieList where boxOffice is less than or equal to SMALLER_BOX_OFFICE
        defaultMovieShouldNotBeFound("boxOffice.lessThanOrEqual=" + SMALLER_BOX_OFFICE);
    }

    @Test
    @Transactional
    public void getAllMoviesByBoxOfficeIsLessThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where boxOffice is less than DEFAULT_BOX_OFFICE
        defaultMovieShouldNotBeFound("boxOffice.lessThan=" + DEFAULT_BOX_OFFICE);

        // Get all the movieList where boxOffice is less than UPDATED_BOX_OFFICE
        defaultMovieShouldBeFound("boxOffice.lessThan=" + UPDATED_BOX_OFFICE);
    }

    @Test
    @Transactional
    public void getAllMoviesByBoxOfficeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movieList where boxOffice is greater than DEFAULT_BOX_OFFICE
        defaultMovieShouldNotBeFound("boxOffice.greaterThan=" + DEFAULT_BOX_OFFICE);

        // Get all the movieList where boxOffice is greater than SMALLER_BOX_OFFICE
        defaultMovieShouldBeFound("boxOffice.greaterThan=" + SMALLER_BOX_OFFICE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMovieShouldBeFound(String filter) throws Exception {
        restMovieMockMvc.perform(get("/api/movies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movie.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].releaseDate").value(hasItem(DEFAULT_RELEASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].runtime").value(hasItem(DEFAULT_RUNTIME.toString())))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].director").value(hasItem(DEFAULT_DIRECTOR)))
            .andExpect(jsonPath("$.[*].writers").value(hasItem(DEFAULT_WRITERS)))
            .andExpect(jsonPath("$.[*].actors").value(hasItem(DEFAULT_ACTORS)))
            .andExpect(jsonPath("$.[*].plot").value(hasItem(DEFAULT_PLOT)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].awards").value(hasItem(DEFAULT_AWARDS)))
            .andExpect(jsonPath("$.[*].posterLink").value(hasItem(DEFAULT_POSTER_LINK)))
            .andExpect(jsonPath("$.[*].ratings").value(hasItem(DEFAULT_RATINGS)))
            .andExpect(jsonPath("$.[*].metascore").value(hasItem(DEFAULT_METASCORE)))
            .andExpect(jsonPath("$.[*].imdbRating").value(hasItem(DEFAULT_IMDB_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].imdbVotes").value(hasItem(DEFAULT_IMDB_VOTES)))
            .andExpect(jsonPath("$.[*].imdbId").value(hasItem(DEFAULT_IMDB_ID)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].dvdReleaseDate").value(hasItem(DEFAULT_DVD_RELEASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].boxOffice").value(hasItem(DEFAULT_BOX_OFFICE)));

        // Check, that the count call also returns 1
        restMovieMockMvc.perform(get("/api/movies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMovieShouldNotBeFound(String filter) throws Exception {
        restMovieMockMvc.perform(get("/api/movies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMovieMockMvc.perform(get("/api/movies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingMovie() throws Exception {
        // Get the movie
        restMovieMockMvc.perform(get("/api/movies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovie() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        int databaseSizeBeforeUpdate = movieRepository.findAll().size();

        // Update the movie
        Movie updatedMovie = movieRepository.findById(movie.getId()).get();
        // Disconnect from session so that the updates on updatedMovie are not directly saved in db
        em.detach(updatedMovie);
        updatedMovie
            .title(UPDATED_TITLE)
            .year(UPDATED_YEAR)
            .rate(UPDATED_RATE)
            .releaseDate(UPDATED_RELEASE_DATE)
            .runtime(UPDATED_RUNTIME)
            .genre(UPDATED_GENRE)
            .director(UPDATED_DIRECTOR)
            .writers(UPDATED_WRITERS)
            .actors(UPDATED_ACTORS)
            .plot(UPDATED_PLOT)
            .language(UPDATED_LANGUAGE)
            .country(UPDATED_COUNTRY)
            .awards(UPDATED_AWARDS)
            .posterLink(UPDATED_POSTER_LINK)
            .ratings(UPDATED_RATINGS)
            .metascore(UPDATED_METASCORE)
            .imdbRating(UPDATED_IMDB_RATING)
            .imdbVotes(UPDATED_IMDB_VOTES)
            .imdbId(UPDATED_IMDB_ID)
            .type(UPDATED_TYPE)
            .dvdReleaseDate(UPDATED_DVD_RELEASE_DATE)
            .boxOffice(UPDATED_BOX_OFFICE);
        MovieDTO movieDTO = movieMapper.toDto(updatedMovie);

        restMovieMockMvc.perform(put("/api/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(movieDTO)))
            .andExpect(status().isOk());

        // Validate the Movie in the database
        List<Movie> movieList = movieRepository.findAll();
        assertThat(movieList).hasSize(databaseSizeBeforeUpdate);
        Movie testMovie = movieList.get(movieList.size() - 1);
        assertThat(testMovie.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMovie.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testMovie.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testMovie.getReleaseDate()).isEqualTo(UPDATED_RELEASE_DATE);
        assertThat(testMovie.getRuntime()).isEqualTo(UPDATED_RUNTIME);
        assertThat(testMovie.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testMovie.getDirector()).isEqualTo(UPDATED_DIRECTOR);
        assertThat(testMovie.getWriters()).isEqualTo(UPDATED_WRITERS);
        assertThat(testMovie.getActors()).isEqualTo(UPDATED_ACTORS);
        assertThat(testMovie.getPlot()).isEqualTo(UPDATED_PLOT);
        assertThat(testMovie.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testMovie.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testMovie.getAwards()).isEqualTo(UPDATED_AWARDS);
        assertThat(testMovie.getPosterLink()).isEqualTo(UPDATED_POSTER_LINK);
        assertThat(testMovie.getRatings()).isEqualTo(UPDATED_RATINGS);
        assertThat(testMovie.getMetascore()).isEqualTo(UPDATED_METASCORE);
        assertThat(testMovie.getImdbRating()).isEqualTo(UPDATED_IMDB_RATING);
        assertThat(testMovie.getImdbVotes()).isEqualTo(UPDATED_IMDB_VOTES);
        assertThat(testMovie.getImdbId()).isEqualTo(UPDATED_IMDB_ID);
        assertThat(testMovie.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMovie.getDvdReleaseDate()).isEqualTo(UPDATED_DVD_RELEASE_DATE);
        assertThat(testMovie.getBoxOffice()).isEqualTo(UPDATED_BOX_OFFICE);
    }

    @Test
    @Transactional
    public void updateNonExistingMovie() throws Exception {
        int databaseSizeBeforeUpdate = movieRepository.findAll().size();

        // Create the Movie
        MovieDTO movieDTO = movieMapper.toDto(movie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMovieMockMvc.perform(put("/api/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(movieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Movie in the database
        List<Movie> movieList = movieRepository.findAll();
        assertThat(movieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMovie() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        int databaseSizeBeforeDelete = movieRepository.findAll().size();

        // Delete the movie
        restMovieMockMvc.perform(delete("/api/movies/{id}", movie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Movie> movieList = movieRepository.findAll();
        assertThat(movieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
