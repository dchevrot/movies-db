import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IComment, Comment } from 'app/shared/model/comment.model';
import { CommentService } from './comment.service';
import { IMovie } from 'app/shared/model/movie.model';
import { MovieService } from 'app/entities/movie/movie.service';

@Component({
  selector: 'jhi-comment-update',
  templateUrl: './comment-update.component.html',
})
export class CommentUpdateComponent implements OnInit {
  isSaving = false;
  movies: IMovie[] = [];

  editForm = this.fb.group({
    id: [],
    body: [null, [Validators.required, Validators.maxLength(1000)]],
    movieId: [null, Validators.required],
  });

  constructor(
    protected commentService: CommentService,
    protected movieService: MovieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comment }) => {
      this.updateForm(comment);

      this.movieService.query().subscribe((res: HttpResponse<IMovie[]>) => (this.movies = res.body || []));
    });
  }

  updateForm(comment: IComment): void {
    this.editForm.patchValue({
      id: comment.id,
      body: comment.body,
      movieId: comment.movieId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const comment = this.createFromForm();
    if (comment.id !== undefined) {
      this.subscribeToSaveResponse(this.commentService.update(comment));
    } else {
      this.subscribeToSaveResponse(this.commentService.create(comment));
    }
  }

  private createFromForm(): IComment {
    return {
      ...new Comment(),
      id: this.editForm.get(['id'])!.value,
      body: this.editForm.get(['body'])!.value,
      movieId: this.editForm.get(['movieId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComment>>): void {
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

  trackById(index: number, item: IMovie): any {
    return item.id;
  }
}
