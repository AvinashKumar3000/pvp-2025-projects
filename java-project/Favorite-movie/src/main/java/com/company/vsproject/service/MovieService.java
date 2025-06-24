package com.company.vsproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.vsproject.entity.Movie;
import com.company.vsproject.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }
    public Movie getMovieById(Long id) {
        return this.movieRepository.findById(id).orElse(null);
    }
    public List<Movie> getMoviesByCategory(String category) {
        return this.movieRepository.findByCategory(category);
    }
    public List<Movie> getMoviesUnderYear(Integer year) {
        List<Movie> allMovies = this.movieRepository.findAll();
        List<Movie> list = new ArrayList<Movie>();
        for (Movie movie : allMovies) {
            if(year <= movie.getYear()) {
                list.add(movie);
            }
        }
        return list;
    }
    public void addMovie(Movie newMovie) {
        this.movieRepository.save(newMovie);
    }
    public void updateMovie(Movie movie) throws Exception {
        Movie existingMovie = this.movieRepository.findById(movie.getId()).orElse(null);
        if(existingMovie == null) {
            throw new Exception("movie with given id not available");
        }
        existingMovie.setCategory(movie.getCategory());
        existingMovie.setName(movie.getName());
        existingMovie.setYear(movie.getYear());
        existingMovie.setDirector(movie.getDirector());
        this.movieRepository.save(existingMovie);
    }
    public void deleteMovie(Long movieId) throws Exception {
        Movie existingMovie = this.movieRepository.findById(movieId).orElse(null);
        if(existingMovie == null) {
            throw new Exception("movie with given id not available");
        }
        this.movieRepository.deleteById(existingMovie.getId());
    }
}
