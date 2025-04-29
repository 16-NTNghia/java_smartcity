package com.personal.smartcity.mappers;

import com.personal.smartcity.dto.requests.Hotel.CreateHotel;
import com.personal.smartcity.dto.requests.Hotel.UpdateHotel;
import com.personal.smartcity.dto.responses.HotelResponse;
import com.personal.smartcity.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelResponse hotelToHotelResponse(Hotel hotel);
    Hotel createHotelMapper(CreateHotel createHotel);
    Hotel updateHotelMapper(@MappingTarget Hotel hotel, UpdateHotel updateHotel);
}
