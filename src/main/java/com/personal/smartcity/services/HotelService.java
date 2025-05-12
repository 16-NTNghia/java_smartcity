package com.personal.smartcity.services;

import com.personal.smartcity.dto.requests.Hotel.CreateHotel;
import com.personal.smartcity.dto.requests.Hotel.UpdateHotel;
import com.personal.smartcity.dto.responses.HotelResponse;
import com.personal.smartcity.entities.Hotel;
import com.personal.smartcity.exceptions.ErrorCode;
import com.personal.smartcity.exceptions.ValidException;
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
        List<HotelResponse> hotelResponses = hotelRepository.findTrash().stream().map(hotelMapper::hotelToHotelResponse).toList();
        return hotelResponses;
    }

    public List<HotelResponse> getAllHotels(){
        List<HotelResponse> hotelResponses = hotelRepository.findAllByIsDeleted().stream().map(hotelMapper::hotelToHotelResponse).toList();
        return hotelResponses;
    }

    public HotelResponse getHotelById(String id){
        Hotel hotel = hotelRepository.fillByIDIsDeleted(id).orElseThrow(() -> new ValidException(ErrorCode.HOTEL_NOTFOUND));
        return hotelMapper.hotelToHotelResponse(hotel);
    }

    public HotelResponse createNewHotel(CreateHotel createHotel){
        if(hotelRepository.existsByPhoneNum(createHotel.getPhoneNum())){
            throw new ValidException(ErrorCode.PHONE_NUMBER_EXISTS);
        }

        if(hotelRepository.existsByEmail(createHotel.getEmail())){
            throw new ValidException(ErrorCode.EMAIL_EXISTS);
        }

        if(hotelRepository.existsHotel(createHotel.getName(), createHotel.getAddress(), createHotel.getWard(), createHotel.getDistrict())){
            throw new ValidException(ErrorCode.HOTEL_EXISTS);
        }

        Hotel newHotel = hotelMapper.createHotelMapper(createHotel);
        hotelRepository.save(newHotel);
        return hotelMapper.hotelToHotelResponse(newHotel);
    }

    public HotelResponse modifyHotel(String id, UpdateHotel updateHotel){
        Hotel modifyHotel = hotelRepository.findById(id).orElseThrow(() -> new ValidException(ErrorCode.HOTEL_NOTFOUND));

        if(updateHotel.getName() == null){
            updateHotel.setName(modifyHotel.getName());
        }

        if(updateHotel.getDescription() == null){
            updateHotel.setDescription(modifyHotel.getDescription());
        }

        if(updateHotel.getAddress() == null){
            updateHotel.setAddress(modifyHotel.getAddress());
        }

        if(updateHotel.getWard() == null){
            updateHotel.setWard(modifyHotel.getWard());
        }

        if(updateHotel.getDistrict() == null){
            updateHotel.setDistrict(modifyHotel.getDistrict());
        }

        if(updateHotel.getPhoneNum() == null){
            updateHotel.setPhoneNum(modifyHotel.getPhoneNum());
        }

        if(updateHotel.getEmail() == null){
            updateHotel.setEmail(modifyHotel.getEmail());
        }

        hotelMapper.updateHotelMapper(modifyHotel, updateHotel);
        hotelRepository.save(modifyHotel);
        return hotelMapper.hotelToHotelResponse(modifyHotel);
    }

    public String deleteHotel(String id){
        Hotel hotel = hotelRepository.fillByIDIsDeleted(id).orElseThrow(() -> new ValidException(ErrorCode.HOTEL_NOTFOUND));
        hotel.setDeleted(true);
        hotelRepository.save(hotel);
        return "Delete Hotel Successfully";
    }
}
