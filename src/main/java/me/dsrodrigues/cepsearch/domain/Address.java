package me.dsrodrigues.cepsearch.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue
	@Column(name="address_id")
	private Long id;
	
	@NotNull
	@NotEmpty
	@Size(min = 8, max = 8)
	@Column
	private String zipCode;

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
