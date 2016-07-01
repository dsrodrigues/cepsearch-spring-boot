package me.dsrodrigues.cepsearch.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dsrodrigues.cepsearch.repository.converter.ZipCodeConverter;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

	private static final long serialVersionUID = 4930170689366259292L;

	@Id
	@GeneratedValue
	@Column(name = "address_id")
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 8, max = 8)
	@Column
	@Convert(converter = ZipCodeConverter.class)
	private ZipCode zipCode;

	@NotNull
	@NotEmpty
	@Size(max = 150)
	@Column
	private String street;

	@NotNull
	@NotEmpty
	@Size(max = 100)
	@Column
	private String neighborhood;

	@NotNull
	@NotEmpty
	@Size(max = 100)
	@Column
	private String city;

	@NotNull
	@NotEmpty
	@Size(max = 25)
	@Column
	private String state;
}
