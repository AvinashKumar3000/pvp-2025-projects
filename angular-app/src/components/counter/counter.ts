import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { StorageService } from '../../services/storageService';

@Component({
  selector: 'app-counter',
  imports: [],
  templateUrl: './counter.html',
  styleUrl: './counter.css'
})
export class Counter {
  storageService = inject(StorageService);
  count = 0;

  update(): void {
    this.count++;
    this.storageService.updateLike();
  }
}
