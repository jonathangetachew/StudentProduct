package edu.mum.studentproduct.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Jonathan on 10/16/2019.
 */

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"supplier"}, callSuper = true)
public class Product extends BaseEntity {

	@NotNull(message = "Product Number is Required")
	@Column(name = "product_number")
	private Long productNumber;

	@NotBlank(message = "Name is Required")
	private String name;

	@NotNull(message = "* Unit Price is required")
	@Digits(integer = 9, fraction = 2, message = "Unit Price must be a numeric/monetary amount in decimal (money) format as 'xxx.xx'")
	@NumberFormat(pattern = "#,###.##")
	@Column(name = "unit_price")
	private Double unitPrice;

	@NotNull(message = "Quantity in Store is Required")
	@Digits(integer = 9, fraction = 0, message = "Quantity Must be a Positive Integer Value")
	@PositiveOrZero(message = "Quantity Must be a Positive Integer Value")
	@Column(name = "quantity_in_store")
	private Integer quantityInStore;

	@NotNull(message = "Date Supplied is Required")
	@Column(name = "date_supplied")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateSupplied;

	@ManyToOne
	@JoinColumn(name = "supplierId", nullable = false)
	@NotNull(message = "Supplier is Required")
	private Supplier supplier;
}
