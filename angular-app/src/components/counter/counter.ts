import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-counter',
  imports: [],
  templateUrl: './counter.html',
  styleUrl: './counter.css'
})
export class Counter {
  @Output() incr = new EventEmitter<void>();
  count = 0;

  update(): void {
    this.count++;
    this.incr.emit();
  }
}
