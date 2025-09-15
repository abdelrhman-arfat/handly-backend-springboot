package com.project.handly.Services;

import com.project.handly.Entities.Country;
import com.project.handly.Exceptions.GlobalExceptionHandler;
import com.project.handly.Repositories.CountryRepo;
import com.project.handly.Utils.ResponseHandler;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryService {
    private final CountryRepo countryRepo;

    public CountryService(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    @Cacheable(value = "countries", key = "#page + '-' + #limit + '-' + (#name != null ? #name : '')")
    public ResponseHandler.PageResponse<Country> all(int page, int limit, String name) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Country> result;

        if (name != null && !name.isEmpty()) {
            result = countryRepo.findByNameEnContainingIgnoreCaseOrNameArContainingIgnoreCase(name, name, pageable);
        } else {
            result = countryRepo.findAll(pageable);
        }
        return ResponseHandler.handlePageResponse(result);
    }

    @Cacheable(value = "country", key = "#id")
    public Country findById(long id) {
        return countryRepo.findById(id)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Country with id " + id + " not found"));
    }


}
