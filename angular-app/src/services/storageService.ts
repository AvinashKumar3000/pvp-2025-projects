import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  private totalCount: number;
  private readonly likeSubject = new Subject<number>();
  readonly like$ = this.likeSubject.asObservable();

  constructor(){
    this.totalCount = 0;
  }
  updateLike() {
    this.totalCount++;
    this.likeSubject.next(this.totalCount)
  }


}
