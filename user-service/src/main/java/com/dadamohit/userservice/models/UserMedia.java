package com.dadamohit.userservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_media")
public class UserMedia {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "media_sequence", sequenceName = "media_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_sequence")
  private Long id;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "media_id")
  private String mediaId;
}
