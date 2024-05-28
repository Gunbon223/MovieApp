package org.gb.movieapp.Service;

import org.gb.movieapp.entites.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountry();

    Country getCountryById(int id);
}
