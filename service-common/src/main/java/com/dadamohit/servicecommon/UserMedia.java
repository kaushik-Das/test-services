package com.dadamohit.servicecommon;

import java.nio.ByteBuffer;
import lombok.Data;

@Data
public class UserMedia {

  private Long userId;
  private ByteBuffer profilePic;
  private ByteBuffer aadharCard;
}
