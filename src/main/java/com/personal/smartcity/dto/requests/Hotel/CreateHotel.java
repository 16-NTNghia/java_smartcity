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
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must be number.")
    String phoneNum;
    @Email(message = "Email is not in correct format.")
    String email;
}
