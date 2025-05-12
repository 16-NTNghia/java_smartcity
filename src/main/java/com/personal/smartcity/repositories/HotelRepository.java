package com.personal.smartcity.repositories;

import com.personal.smartcity.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
    @Query("select h from Hotel h where h.deleted = false")
    Set<Hotel> findAllByIsDeleted();

    @Query("select h from Hotel h where h.deleted = true")
    Set<Hotel> findTrash();

    @Query("select h from Hotel h where h.deleted = false and h.id = :id")
    Optional<Hotel> fillByIDIsDeleted(@Param("id") String id);

    boolean existsByPhoneNum(String phoneNum);
    boolean existsByEmail(String email);

    @Query("select case when count(h) > 0 then true else false end from Hotel h where h.name = :name " +
            "and h.address = :address and h.ward = :ward and h.district = :district")
    boolean existsHotel(@Param("name") String name, @Param("address") String address,
                        @Param("ward") String ward, @Param("district") String district);
}
