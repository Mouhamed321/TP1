package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value = "SELECT SUM(city.population) AS population " +
            "From Country country Inner Join City city ON city.country_id = country.id " +
            "Where country.id = :countryId ", nativeQuery = true)
    public Integer calculPopulationByCountryID(Integer id);

    @Query(value = "SELECT country.id AS ID, SUM(population) AS population " +
            "FROM Country country " +
            "INNER JOIN City city ON co.id = ci.country_id " +
            "GROUP BY id"
            , nativeQuery = true)
    public List<PopulationByCountry> getPopulation();

}
