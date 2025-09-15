package com.project.handly.Controllers;


import com.project.handly.Entities.Country;
import com.project.handly.Services.CountryService;
import com.project.handly.Utils.ResponseHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping
    public ResponseEntity<Object> getCountries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int limit,
            @RequestParam(required = false) String name
    ) {
        try {
            ResponseHandler.PageResponse<Country> countries = countryService.all(page, limit , name);
            return ResponseHandler.success("success fetch countries", countries, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.error("Error in get all country : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id) {
        try {
            Country countries = countryService.findById(id);
            return ResponseHandler.success("success fetch countries", countries , HttpStatus.OK);
        }catch (Exception e){
            return  ResponseHandler.error("Error in get all country : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
