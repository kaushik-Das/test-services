package com.dadamohit.mediaservice.models;

import com.dadamohit.servicecommon.models.Media;
import java.util.List;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserMedia {

  private Long userId;
  private List<Media> medias;
}
