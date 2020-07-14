export interface IComment {
  id?: number;
  body?: string;
  movieId?: number;
}

export class Comment implements IComment {
  constructor(public id?: number, public body?: string, public movieId?: number) {}
}
