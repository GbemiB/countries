package com.gbemisola.countries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbemisola.countries.domain.ApiResponse;
import com.gbemisola.countries.entity.Country;
import com.gbemisola.countries.repository.CountryRepository;
import com.gbemisola.countries.service.ICountryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryRepository countryRepositoryMock;

    @MockBean
    private ICountryService countryServiceMock;

    @Test
    @DisplayName("Test to get all countries and check for status code of 200")
    void should_Return_Status_200_For_Get_All_Countries() throws Exception {
        List<Country> countryResponse = new ArrayList<>();
        ApiResponse apiResponse = new ApiResponse();
        Integer pageNo = 0;
        Integer pageSize = 5;
        String sortBy = "countryName";

        Mockito.when(this.countryRepositoryMock.findAll())
                .thenReturn(countryResponse);

        Mockito.when(this.countryServiceMock.getAllCountries(pageNo, pageSize, sortBy))
                .thenReturn(new ApiResponse());

        this.mockMvc.perform(get("/countries/sort").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(asJsonString(apiResponse)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test to get all countries and check for status code of 500")
    void should_Return_Status_500_For_Get_All_Countries() throws Exception {
        List<Country> countryResponse = new ArrayList<>();
        ApiResponse apiResponse = new ApiResponse();
        Integer pageNo = 0;
        Integer pageSize = 5;
        String sortBy = "countryName";

        Mockito.when(this.countryRepositoryMock.findAll())
                .thenReturn(countryResponse);

        Mockito.when(this.countryServiceMock.getAllCountries(pageNo, pageSize, sortBy))
                .thenThrow(Exception.class);

        this.mockMvc.perform(get("/countries/sort").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(asJsonString(apiResponse)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Test to create new country and check for status code of 200")
    void should_Return_Status_200_For_Create_Country() throws Exception {
        Country countryRequest = new Country();
        ApiResponse apiResponse = new ApiResponse();

        Mockito.when(this.countryRepositoryMock.save(countryRequest))
                .thenReturn(new Country());

        Mockito.when(this.countryServiceMock.createCountries(countryRequest))
                .thenReturn(new ApiResponse());

        this.mockMvc.perform(post("/countries/create").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(asJsonString(apiResponse)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test to create new country and check for status code of 500")
    void should_Return_Status_500_For_Create_Country() throws Exception {
        Country countryRequest = new Country();
        ApiResponse apiResponse = new ApiResponse();

        Mockito.when(this.countryRepositoryMock.save(countryRequest))
                .thenThrow(Exception.class);

        Mockito.when(this.countryServiceMock.createCountries(countryRequest))
                .thenReturn(new ApiResponse());

        this.mockMvc.perform(post("/countries/create").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(asJsonString(apiResponse)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Test to paginate and sort countries and check for status code of 200")
    void should_Return_Status_200_For_Paginate_Country() throws Exception {
        Country countryRequest = new Country();
        ApiResponse apiResponse = new ApiResponse();
        Integer id = 4;

        Mockito.when(this.countryRepositoryMock.findById(countryRequest.getId()))
                .thenReturn(Optional.of(new Country()));

        Mockito.when(this.countryServiceMock.createCountries(countryRequest))
                .thenReturn(new ApiResponse());

        this.mockMvc.perform(post("/countries/id").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(asJsonString(apiResponse)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test to paginate and sort countries and check for status code of 404")
    void should_Return_Status_404_For_Paginate_Country() throws Exception {
        Country countryRequest = new Country();
        ApiResponse apiResponse = new ApiResponse();
        Integer id = 4;

        Mockito.when(this.countryRepositoryMock.findById(countryRequest.getId()))
                .thenReturn(Optional.empty());

        Mockito.when(this.countryServiceMock.createCountries(countryRequest))
                .thenReturn(new ApiResponse());

        this.mockMvc.perform(post("/countries/id").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(asJsonString(apiResponse)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test to paginate and sort countries and check for status code of 500")
    void should_Return_Status_500_For_Paginate_Country() throws Exception {
        Country countryRequest = new Country();
        ApiResponse apiResponse = new ApiResponse();
        Integer id = 4;

        Mockito.when(this.countryRepositoryMock.save(countryRequest))
                .thenThrow(Exception.class);

        Mockito.when(this.countryServiceMock.createCountries(countryRequest))
                .thenReturn(new ApiResponse());

        this.mockMvc.perform(post("/countries/id").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(asJsonString(apiResponse)))
                .andExpect(status().isInternalServerError());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
