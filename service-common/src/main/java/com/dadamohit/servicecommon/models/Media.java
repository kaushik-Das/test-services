package com.dadamohit.servicecommon.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Media {

  private String id;
  private final String userId;
  private final String name;
  private final String type;
  private final byte[] content;
}
