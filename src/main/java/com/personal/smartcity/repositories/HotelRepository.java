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
    Set<Hotel> fillAllByIsDeleted();

    @Query("select h from Hotel h where h.deleted = true")
    Set<Hotel> fillTrash();

    @Query("select h from Hotel h where h.deleted = false and h.id = :id")
    Optional<Hotel> fillByIDIsDeleted(@Param("id") String id);

    boolean existsByPhoneNum(String phoneNum);
    boolean existsByEmail(String email);
}
