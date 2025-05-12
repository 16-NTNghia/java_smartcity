package com.personal.smartcity.dto.requests.Hotel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateHotel {
    String name;
    String description;
    String address;
    String ward;
    String district;
    @Pattern(regexp = "^[0-9]+$", message = "INVALID_PHONE_NUMBER")
    String phoneNum;
    @Email(message = "INVALID_EMAIL")
    String email;
}
