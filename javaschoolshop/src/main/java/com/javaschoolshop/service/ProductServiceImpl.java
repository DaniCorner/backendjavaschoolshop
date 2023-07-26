package com.javaschoolshop.service;

import com.javaschoolshop.dao.ProductCategoryRepository;
import com.javaschoolshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaschoolshop.dao.ProductRepository;

import java.util.*;
@Service
public class ProductServiceImpl implements ProductService {
    private final List<Product> products;
    private final ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository = null;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.products = new ArrayList<>();
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> foundProduct = productRepository.findById(id); //Optional, handling possibly null values
        return foundProduct.orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    //1º: Look for targetMovie (id)
    //2º: Choose the parameter (c,d,o)-->keyword-->substring
    //3º: Find randomly movie with same parameter
    //4º: Try again if is <3, and add, first to Set (temp) and then to ArrayList <SimilarMovie>

    @Override
    public List<Product> findRandomSimilarMovies(Long id) {
        Product targetMovie = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + id));

        String country = extractParameterValue(targetMovie.getParameters(), "COUNTRY:");
        String director = extractParameterValue(targetMovie.getParameters(), "DIRECTOR:");
        String other = extractParameterValue(targetMovie.getParameters(), "OTHER:");

        Set<Product> similarMovies = new HashSet<>(); //Set, to not send duplicates

        if (country != null && similarMovies.size() < 3) {
            Product similarCountryMovie = findRandomMovieByParameter(country, id);
            if (similarCountryMovie != null && !similarCountryMovie.equals(targetMovie)) {
                similarMovies.add(similarCountryMovie);
            }
        }
        if (director != null && similarMovies.size() < 3) {
            Product similarDirectorMovie = findRandomMovieByParameter(director, id);
            if (similarDirectorMovie != null && !similarDirectorMovie.equals(targetMovie)) {
                similarMovies.add(similarDirectorMovie);
            }
        }
        if (other != null && similarMovies.size() < 3) {
            Product similarOtherMovie = findRandomMovieByParameter(other, id);
            if (similarOtherMovie != null && !similarOtherMovie.equals(targetMovie)) {
                similarMovies.add(similarOtherMovie);
            }
        }
        // TRY AGAIN
        // If there are still less than 3 similar movies
        while (similarMovies.size() < 3) {
            // Search in the remaining parameters
            if (country != null) {
                Product similarCountryMovie = findRandomMovieByParameter(country, id);
                if (similarCountryMovie != null && !similarCountryMovie.equals(targetMovie)) {
                    similarMovies.add(similarCountryMovie);
                }
            }
            if (director != null && similarMovies.size() < 3) {
                Product similarDirectorMovie = findRandomMovieByParameter(director, id);
                if (similarDirectorMovie != null && !similarDirectorMovie.equals(targetMovie)) {
                    similarMovies.add(similarDirectorMovie);
                }
            }
            if (other != null && similarMovies.size() < 3) {
                Product similarOtherMovie = findRandomMovieByParameter(other, id);
                if (similarOtherMovie != null && !similarOtherMovie.equals(targetMovie)) {
                    similarMovies.add(similarOtherMovie);
                }
            }
            // Break the loop to avoid it not ending if no additional movies are found
            break;
        }

        List<Product> uniqueSimilarMovies = new ArrayList<>(similarMovies);

        if (uniqueSimilarMovies.isEmpty()) {
            throw new IllegalStateException("No similar movies found.");
        }

        return uniqueSimilarMovies;
    }

    private String extractParameterValue(String parameters, String keyword) {
        int startIndex = parameters.indexOf(keyword);
        if (startIndex != -1) { //If keyword is in the string, startIndex is not -1
            int endIndex = parameters.indexOf(";", startIndex);
            if (endIndex != -1) { //If ";" is in the string, then...
                return parameters.substring(startIndex + keyword.length(), endIndex).trim();
                // Search the word after K with substring, it starts after sI+K.l to not include also the keyword
            }
        }
        return null;
    }

    private Product findRandomMovieByParameter(String parameter, Long id) {
        List<Product> movies = productRepository.findByParametersContainingAndIdNot(parameter, id); //Not the same ID that actual m
        if (movies.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(movies.size()); //NextInt(limit): Random number size of quantity of movies
        return movies.get(randomIndex);
    }
}
