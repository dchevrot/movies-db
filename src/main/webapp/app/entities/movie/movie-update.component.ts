import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMovie, Movie } from 'app/shared/model/movie.model';
import { MovieService } from './movie.service';

@Component({
  selector: 'jhi-movie-update',
  templateUrl: './movie-update.component.html',
})
export class MovieUpdateComponent implements OnInit {
  isSaving = false;
  releaseDateDp: any;
  dvdReleaseDateDp: any;

  editForm = this.fb.group({
    id: [],
    title: [null, [Validators.required]],
    year: [],
    rate: [],
    releaseDate: [],
    runtime: [],
    genre: [],
    director: [],
    writers: [],
    actors: [],
    plot: [],
    language: [],
    country: [],
    awards: [],
    posterLink: [],
    ratings: [],
    metascore: [],
    imdbRating: [],
    imdbVotes: [],
    imdbId: [null, [Validators.required]],
    type: [],
    dvdReleaseDate: [],
    boxOffice: [],
  });

  constructor(protected movieService: MovieService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ movie }) => {
      this.updateForm(movie);
    });
  }

  updateForm(movie: IMovie): void {
    this.editForm.patchValue({
      id: movie.id,
      title: movie.title,
      year: movie.year,
      rate: movie.rate,
      releaseDate: movie.releaseDate,
      runtime: movie.runtime,
      genre: movie.genre,
      director: movie.director,
      writers: movie.writers,
      actors: movie.actors,
      plot: movie.plot,
      language: movie.language,
      country: movie.country,
      awards: movie.awards,
      posterLink: movie.posterLink,
      ratings: movie.ratings,
      metascore: movie.metascore,
      imdbRating: movie.imdbRating,
      imdbVotes: movie.imdbVotes,
      imdbId: movie.imdbId,
      type: movie.type,
      dvdReleaseDate: movie.dvdReleaseDate,
      boxOffice: movie.boxOffice,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const movie = this.createFromForm();
    if (movie.id !== undefined) {
      this.subscribeToSaveResponse(this.movieService.update(movie));
    } else {
      this.subscribeToSaveResponse(this.movieService.create(movie));
    }
  }

  private createFromForm(): IMovie {
    return {
      ...new Movie(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      year: this.editForm.get(['year'])!.value,
      rate: this.editForm.get(['rate'])!.value,
      releaseDate: this.editForm.get(['releaseDate'])!.value,
      runtime: this.editForm.get(['runtime'])!.value,
      genre: this.editForm.get(['genre'])!.value,
      director: this.editForm.get(['director'])!.value,
      writers: this.editForm.get(['writers'])!.value,
      actors: this.editForm.get(['actors'])!.value,
      plot: this.editForm.get(['plot'])!.value,
      language: this.editForm.get(['language'])!.value,
      country: this.editForm.get(['country'])!.value,
      awards: this.editForm.get(['awards'])!.value,
      posterLink: this.editForm.get(['posterLink'])!.value,
      ratings: this.editForm.get(['ratings'])!.value,
      metascore: this.editForm.get(['metascore'])!.value,
      imdbRating: this.editForm.get(['imdbRating'])!.value,
      imdbVotes: this.editForm.get(['imdbVotes'])!.value,
      imdbId: this.editForm.get(['imdbId'])!.value,
      type: this.editForm.get(['type'])!.value,
      dvdReleaseDate: this.editForm.get(['dvdReleaseDate'])!.value,
      boxOffice: this.editForm.get(['boxOffice'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMovie>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
