package org.gb.movieapp.Service.ServiceImplement;

import org.gb.movieapp.Repository.CountryRepository;
import org.gb.movieapp.Service.CountryService;
import org.gb.movieapp.entites.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImplement implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }
    @Override
    public Country getCountryById(int id) {
        return countryRepository.findById(id);
    }
}
