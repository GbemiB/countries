package com.gbemisola.countries.repository;

import com.gbemisola.countries.entity.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {

}