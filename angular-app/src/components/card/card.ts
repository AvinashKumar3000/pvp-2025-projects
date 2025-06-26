import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Counter } from "../counter/counter";
import { Movie } from '../../interfaces/MovieInterface';

@Component({
  selector: 'app-card',
  imports: [Counter],
  templateUrl: './card.html',
  styleUrl: './card.css'
})
export class Card {
  @Input("movieDetail") movie:Movie | null = null;
  @Output() updateTotal = new EventEmitter<void>();

  updateLikeCount() {
    this.updateTotal.emit();
  }
}
