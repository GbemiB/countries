package com.gbemisola.countries.controller;

import com.gbemisola.countries.domain.ApiResponse;
import com.gbemisola.countries.entity.Country;
import com.gbemisola.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
public class CountriesController {
    @Autowired
    CountryService countryService;

    @GetMapping("/sort")
    public ApiResponse getAllCountries(@RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy) throws Exception {
        return countryService.getAllCountries(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{param}")
    public ApiResponse getCountriesById(@PathVariable("param") Long param) throws Exception {
        return countryService.getCountriesByParam(param);
    }

    @PostMapping ("/create")
    public ApiResponse createCountry(@RequestBody Country cty) throws Exception {
        return countryService.createCountries(cty);
    }
}