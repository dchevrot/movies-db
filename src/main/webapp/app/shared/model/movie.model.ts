import { Moment } from 'moment';

export interface IMovie {
  id?: number;
  title?: string;
  year?: number;
  rate?: string;
  releaseDate?: Moment;
  runtime?: number;
  genre?: string;
  director?: string;
  writers?: string;
  actors?: string;
  plot?: string;
  language?: string;
  country?: string;
  awards?: string;
  posterLink?: string;
  ratings?: string;
  metascore?: number;
  imdbRating?: number;
  imdbVotes?: number;
  imdbId?: string;
  type?: string;
  dvdReleaseDate?: Moment;
  boxOffice?: number;
}

export class Movie implements IMovie {
  constructor(
    public id?: number,
    public title?: string,
    public year?: number,
    public rate?: string,
    public releaseDate?: Moment,
    public runtime?: number,
    public genre?: string,
    public director?: string,
    public writers?: string,
    public actors?: string,
    public plot?: string,
    public language?: string,
    public country?: string,
    public awards?: string,
    public posterLink?: string,
    public ratings?: string,
    public metascore?: number,
    public imdbRating?: number,
    public imdbVotes?: number,
    public imdbId?: string,
    public type?: string,
    public dvdReleaseDate?: Moment,
    public boxOffice?: number
  ) {}
}
