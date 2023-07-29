package org.finalproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@OptimisticLocking
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Version
    long version;
    @NotEmpty(message = "Field 'country' can't be empty")
    @Size(min = 1, max = 60, message = "Country name must incorporates min 1 and max 60 characters")
    String country;
    @NotEmpty(message = "Field 'city' can't be empty")
    @Size(min = 1, max = 60, message = "City name must incorporates min 1 and max 60 characters")
    String city;
    @NotEmpty(message = "Field 'street' can't be empty")
    @Size(min = 1, max = 60, message = "Street name must incorporates min 1 and max 60 characters")
    String street;
    @NotEmpty(message = "Field 'house' can't be empty")
    @Size(min = 1, max = 60, message = "House name must incorporates min 1 and max 60 characters")
    String house;
}
