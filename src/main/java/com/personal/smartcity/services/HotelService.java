package com.personal.smartcity.services;

import com.personal.smartcity.dto.requests.Hotel.CreateHotel;
import com.personal.smartcity.dto.requests.Hotel.UpdateHotel;
import com.personal.smartcity.dto.responses.HotelResponse;
import com.personal.smartcity.entities.Hotel;
import com.personal.smartcity.mappers.HotelMapper;
import com.personal.smartcity.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    public List<HotelResponse> getTrash(){
        List<HotelResponse> hotelResponses = hotelRepository.fillTrash().stream().map(hotelMapper::hotelToHotelResponse).toList();
        return hotelResponses;
    }

    public List<HotelResponse> getAllHotels(){
        List<HotelResponse> hotelResponses = hotelRepository.fillAllByIsDeleted().stream().map(hotelMapper::hotelToHotelResponse).toList();
        return hotelResponses;
    }

    public HotelResponse getHotelById(String id){
        Hotel hotel = hotelRepository.fillByIDIsDeleted(id).orElseThrow(() -> new Error("Hotel not found"));
        return hotelMapper.hotelToHotelResponse(hotel);
    }

    public HotelResponse createNewHotel(CreateHotel createHotel){
        if(hotelRepository.existsByPhoneNum(createHotel.getPhoneNum())){
            throw new Error("Phone number already exists");
        }

        if(hotelRepository.existsByEmail(createHotel.getEmail())){
            throw new Error("Email already exists");
        }

        Hotel newHotel = hotelMapper.createHotelMapper(createHotel);
        hotelRepository.save(newHotel);
        return hotelMapper.hotelToHotelResponse(newHotel);
    }

    public HotelResponse modifyHotel(String id, UpdateHotel updateHotel){
        Hotel modifyHotel = hotelRepository.findById(id).orElseThrow(() -> new Error("Hotel not found"));
        hotelMapper.updateHotelMapper(modifyHotel, updateHotel);
        hotelRepository.save(modifyHotel);
        return hotelMapper.hotelToHotelResponse(modifyHotel);
    }

    public String deleteHotel(String id){
        Hotel hotel = hotelRepository.fillByIDIsDeleted(id).orElseThrow(() -> new Error("Hotel not found"));
        hotel.setDeleted(true);
        hotelRepository.save(hotel);
        return "Delete Hotel Successfully";
    }
}
