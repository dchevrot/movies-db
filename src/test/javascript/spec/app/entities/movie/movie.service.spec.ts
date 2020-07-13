import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { MovieService } from 'app/entities/movie/movie.service';
import { IMovie, Movie } from 'app/shared/model/movie.model';

describe('Service Tests', () => {
  describe('Movie Service', () => {
    let injector: TestBed;
    let service: MovieService;
    let httpMock: HttpTestingController;
    let elemDefault: IMovie;
    let expectedResult: IMovie | IMovie[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MovieService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Movie(
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            releaseDate: currentDate.format(DATE_FORMAT),
            dvdReleaseDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Movie', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            releaseDate: currentDate.format(DATE_FORMAT),
            dvdReleaseDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            releaseDate: currentDate,
            dvdReleaseDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Movie()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Movie', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            year: 1,
            rate: 'BBBBBB',
            releaseDate: currentDate.format(DATE_FORMAT),
            runtime: 'BBBBBB',
            genre: 'BBBBBB',
            director: 'BBBBBB',
            writers: 'BBBBBB',
            actors: 'BBBBBB',
            plot: 'BBBBBB',
            language: 'BBBBBB',
            country: 'BBBBBB',
            awards: 'BBBBBB',
            posterLink: 'BBBBBB',
            ratings: 'BBBBBB',
            metascore: 1,
            imdbRating: 1,
            imdbVotes: 1,
            imdbId: 'BBBBBB',
            type: 'BBBBBB',
            dvdReleaseDate: currentDate.format(DATE_FORMAT),
            boxOffice: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            releaseDate: currentDate,
            dvdReleaseDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Movie', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            year: 1,
            rate: 'BBBBBB',
            releaseDate: currentDate.format(DATE_FORMAT),
            runtime: 'BBBBBB',
            genre: 'BBBBBB',
            director: 'BBBBBB',
            writers: 'BBBBBB',
            actors: 'BBBBBB',
            plot: 'BBBBBB',
            language: 'BBBBBB',
            country: 'BBBBBB',
            awards: 'BBBBBB',
            posterLink: 'BBBBBB',
            ratings: 'BBBBBB',
            metascore: 1,
            imdbRating: 1,
            imdbVotes: 1,
            imdbId: 'BBBBBB',
            type: 'BBBBBB',
            dvdReleaseDate: currentDate.format(DATE_FORMAT),
            boxOffice: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            releaseDate: currentDate,
            dvdReleaseDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Movie', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
