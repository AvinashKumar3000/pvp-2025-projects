import { Component } from '@angular/core';
import { Card } from '../components/card/card';
import { Movie } from '../interfaces/MovieInterface';


@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [Card]
})
export class App {
  totalLikeCount:number = 0;
  favorites:Movie[] = [];
  movieList:Movie[] = MOVIES;

  addToFavorite(index:number):void{
    let movie:Movie = this.movieList[index];
    movie.isFavorite = false;
    this.favorites.push(movie);
  }

  updateTotal():void {
    console.log("triggered");
    this.totalLikeCount++;
  }
}

const MOVIES: Movie[] = [
  {
    imageUrl: 'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg',
    desc: 'A skilled thief is given a chance at redemption if he can successfully perform inception.',
    title: 'Inception',
    isFavorite: true,
  },
  {
    imageUrl: 'https://m.media-amazon.com/images/M/MV5BYzdjMDAxZGItMjI2My00ODA1LTlkNzItOWFjMDU5ZDJlYWY3XkEyXkFqcGc@._V1_.jpg',
    desc: 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.',
    title: 'Interstellar',
    isFavorite: false,
  },
  {
    imageUrl: 'https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpgg',
    desc: 'Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',
    title: 'The Dark Knight',
    isFavorite: true,
  },
  {
    imageUrl: 'https://m.media-amazon.com/images/M/MV5BNmQxNjZlZTctMWJiMC00NGMxLWJjNTctNTFiNjA1Njk3ZDQ5XkEyXkFqcGc@._V1_.jpg',
    desc: 'A paraplegic Marine is dispatched to the moon Pandora on a unique mission.',
    title: 'Avatar',
    isFavorite: false,
  },
];
