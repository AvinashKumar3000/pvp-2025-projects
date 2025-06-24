package com.company.vsproject.controllers;

import org.springframework.web.bind.annotation.*;

import com.company.vsproject.dto.ResponseDto;
import com.company.vsproject.entity.Movie;
import com.company.vsproject.service.MovieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseDto getAll() {
        ResponseDto response = new ResponseDto();
        try {
            var movies = this.movieService.getAllMovies();
            response.setSuccess(true);
            response.setData(movies);
            response.setMessage("Retrieved all movies");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setData(null);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/by/{id}")
    public ResponseDto getById(@PathVariable("id") Long movieId) {
        ResponseDto response = new ResponseDto();
        try {
            var movie = this.movieService.getMovieById(movieId);
            response.setSuccess(true);
            response.setData(movie);
            response.setMessage("Retrieved movie with given id");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setData(null);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/under/year/{year}")
    public ResponseDto getUnderYear(@PathVariable("year") Integer year) {
        ResponseDto response = new ResponseDto();
        try {
            var movies = this.movieService.getMoviesUnderYear(year);
            response.setSuccess(true);
            response.setData(movies);
            response.setMessage("Retrieved movies under given year");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setData(null);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/category/{category}")
    public ResponseDto getByCategory(@PathVariable("category") String category) {
        ResponseDto response = new ResponseDto();
        try {
            var movies = this.movieService.getMoviesByCategory(category);
            response.setSuccess(true);
            response.setData(movies);
            response.setMessage("Retrieved movies by category");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setData(null);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseDto addMovie(@RequestBody Movie newMovie) {
        ResponseDto response = new ResponseDto();
        try {
            this.movieService.addMovie(newMovie);
            response.setSuccess(true);
            response.setMessage("Movie added successfully");
            response.setData(newMovie);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    @PutMapping("/update")
    public ResponseDto updateMovie(@RequestBody Movie updatedMovie) {
        ResponseDto response = new ResponseDto();
        try {
            this.movieService.updateMovie(updatedMovie);
            response.setSuccess(true);
            response.setMessage("Movie updated successfully");
            response.setData(updatedMovie);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteMovie(@PathVariable("id") Long movieId) {
        ResponseDto response = new ResponseDto();
        try {
            this.movieService.deleteMovie(movieId);
            response.setSuccess(true);
            response.setMessage("Movie deleted successfully");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }
}
