package com.personal.smartcity.dto.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponse {
    String id;
    String name;
    String description;
    String address;
    String ward;
    String district;
    String phoneNum;
    String email;
    boolean deleted;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
