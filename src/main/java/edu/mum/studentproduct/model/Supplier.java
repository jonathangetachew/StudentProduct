package edu.mum.studentproduct.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 10/16/2019.
 */

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"products"}, callSuper = true)
public class Supplier extends BaseEntity {

	@NotNull(message = "Supplier Number is Required")
	@Digits(integer = 9, fraction = 0, message = "Supplier must be numeric")
	@Column(name = "supplier_number", nullable = false, unique = true)
	private Integer supplierNumber;

	@NotBlank(message = "Name is Required")
	private String name;

	@Column(name = "contact_phone_number")
	private String contactPhoneNumber;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
	private List<Product> products = new ArrayList<>();
}
