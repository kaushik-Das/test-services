package com.dadamohit.userservice.models;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@NoArgsConstructor
@Data
@Entity(name = "users")
@Table(name = "users")
@TypeDefs({@TypeDef(name="string",defaultForType=java.lang.String.class,typeClass=org.hibernate.type.TextType.class)})
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class User {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
  private Long userId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @Type(type = "list-array")
  @Column(name = "media_ids", columnDefinition = "text[]")
  private List<String> medias;
}
