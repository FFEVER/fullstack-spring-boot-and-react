package com.packt.cardatabase.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/*
Using PagingAndSortingRepository offers method to fetch entities using pagination and sorting:
- Iterable<T> findAll(Sort sort)  Returns all entities sorted by the given options
- Page<T> findAll(Pageable pageable)  Returns all entities according to given paging options
 */
@RepositoryRestResource
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    // Fetch cars by brand
    List<Car> findByBrand(String brand);

    // Fetch cars by color
    List<Car> findByColor(String color);

    // Fetch cars by year
    List<Car> findByYear(Integer year);

    // Fetch cars by brand and model
    List<Car> findByBrandAndModel(String brand, String model);

    // Fetch cars by brand or color
    List<Car> findByBrandOrColor(String brand, String color);

    // Fetch cars by brand and sort by year
    List<Car> findByBrandOrderByYearAsc(String brand);

    // Fetch cars by registerNumber using SQL
    @Query("select c from Car c where c.registerNumber = ?1")
    List<Car> findByRegisterNumber(String registerNumber);

    // Fetch cars by brand using SQL
    @Query("select c from Car c where c.brand like %?1%")
    List<Car> findByBrandContains(String brand);

}
