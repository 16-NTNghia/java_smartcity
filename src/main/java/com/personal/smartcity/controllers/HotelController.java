package com.personal.smartcity.controllers;

import com.personal.smartcity.dto.requests.Hotel.CreateHotel;
import com.personal.smartcity.dto.requests.Hotel.UpdateHotel;
import com.personal.smartcity.dto.responses.ApiResponse;
import com.personal.smartcity.dto.responses.HotelResponse;
import com.personal.smartcity.entities.Hotel;
import com.personal.smartcity.services.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("")
    public ApiResponse<List<HotelResponse>> getAllHotels(){
        ApiResponse<List<HotelResponse>> response = new ApiResponse<>();
        response.setResult(hotelService.getAllHotels());
        return response;
    }

    @GetMapping("/trash")
    public ApiResponse<List<HotelResponse>> getTrash(){
        ApiResponse<List<HotelResponse>> response = new ApiResponse<>();
        response.setResult(hotelService.getTrash());
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<HotelResponse> getHotel(@PathVariable("id") String id){
        ApiResponse<HotelResponse> response = new ApiResponse<>();
        response.setResult(hotelService.getHotelById(id));
        return response;
    }

    @PostMapping("/add")
    public ApiResponse<HotelResponse> createNewHotel(@RequestBody @Valid CreateHotel createHotel){
        ApiResponse<HotelResponse> response = new ApiResponse<>();
        response.setResult(hotelService.createNewHotel(createHotel));
        return response;
    }

    @PutMapping("/modify/{id}")
    public ApiResponse<HotelResponse> modifyHotel(@PathVariable("id") String id, @RequestBody @Valid UpdateHotel updateHotel){
        ApiResponse<HotelResponse> response = new ApiResponse<>();
        response.setResult(hotelService.modifyHotel(id, updateHotel));
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteHotel(@PathVariable("id") String id){
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult(hotelService.deleteHotel(id));
        return response;
    }
}
