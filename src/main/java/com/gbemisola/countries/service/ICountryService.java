package com.gbemisola.countries.service;

import com.gbemisola.countries.domain.ApiResponse;
import com.gbemisola.countries.entity.Country;

public interface ICountryService {
    ApiResponse getAllCountries(Integer pageNo, Integer pageSize, String sortBy) throws Exception;

    ApiResponse getCountriesByParam(Long id) throws Exception;

    ApiResponse createCountries(Country cty) throws Exception;
}
