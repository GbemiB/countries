package com.gbemisola.countries.service;

import com.gbemisola.countries.domain.ApiResponse;
import com.gbemisola.countries.domain.ResponseField;
import com.gbemisola.countries.entity.Country;
import com.gbemisola.countries.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Disabled
@Service
@Slf4j
public class CountryService implements ICountryService {

    @Autowired
    CountryRepository countryRepository;

    //Method  to get all countries and paginate  as appropriate
    public ApiResponse getAllCountries(Integer pageNo, Integer pageSize, String sortBy) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Country> pagedValue = countryRepository.findAll(paging);
            if (pagedValue.hasContent()) {
                apiResponse.setHttpStatus(HttpStatus.OK);
                apiResponse.setResponseCode(ResponseField.SUCCESS.getCode());
                apiResponse.setResponseMessage(ResponseField.SUCCESS.getMessage());
                apiResponse.setResponseContent(pagedValue.getContent());
            } else {
                apiResponse.setHttpStatus(HttpStatus.OK);
                apiResponse.setResponseCode(ResponseField.SUCCESS.getCode());
                apiResponse.setResponseMessage(ResponseField.SUCCESS.getMessage());
                apiResponse.setResponseContent(pagedValue.getContent());
            }
        } catch (Exception exception) {
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return apiResponse;
    }

    //Method  to get all countries and paginate  as appropriate
    @Override
    public ApiResponse getCountriesByParam(Long param) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Optional<Country> country = countryRepository.findById(param);
            if (country.isPresent()) {
                apiResponse.setHttpStatus(HttpStatus.OK);
                apiResponse.setResponseCode(ResponseField.SUCCESS.getCode());
                apiResponse.setResponseMessage(ResponseField.SUCCESS.getMessage());
                apiResponse.setResponseObject(country);

            } else {
                apiResponse.setHttpStatus(HttpStatus.OK);
                apiResponse.setResponseCode(ResponseField.NOT_FOUND.getCode());
                apiResponse.setResponseMessage(ResponseField.NOT_FOUND.getMessage());
            }
        } catch (Exception exception) {
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return apiResponse;
    }

    //Method  to get all countries and paginate  as appropriate
    @Override
    public ApiResponse createCountries(Country cty) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Optional<Country> country = countryRepository.findById(cty.getId());
            if (country.isPresent()) {
                apiResponse.setHttpStatus(HttpStatus.OK);
                apiResponse.setResponseCode(ResponseField.DUPLICATE_RECORD.getCode());
                apiResponse.setResponseMessage(ResponseField.DUPLICATE_RECORD.getMessage());

            } else {
                Country couty = countryRepository.save(cty);
                apiResponse.setHttpStatus(HttpStatus.OK);
                apiResponse.setResponseCode(ResponseField.SUCCESS.getCode());
                apiResponse.setResponseMessage(ResponseField.SUCCESS.getMessage());
                                apiResponse.setResponseObject(couty);
            }
        } catch (Exception exception) {
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return apiResponse;
    }

}