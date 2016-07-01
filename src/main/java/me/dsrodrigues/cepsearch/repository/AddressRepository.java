package me.dsrodrigues.cepsearch.repository;

import me.dsrodrigues.cepsearch.domain.Address;
import me.dsrodrigues.cepsearch.domain.ZipCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query(name="findByZipCode", value="SELECT a FROM Address a WHERE a.zipCode = :zipCode")
	public Address findByZipCode(@Param("zipCode") ZipCode zipCode);
}
