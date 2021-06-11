package com.dadamohit.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Address")
@Table(name = "address")
public class Address {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
  private Long id;

  private String state;

  @Column(name = "zip_code")
  private Integer zipCode;
  private String city;
  private String country;

  @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
  @Exclude
  @JsonIgnore
  private User user;
}
