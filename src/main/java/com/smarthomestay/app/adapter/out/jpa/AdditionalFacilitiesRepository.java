package com.smarthomestay.app.adapter.out.jpa;

import com.smarthomestay.app.domain.RoomAdditionalFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalFacilitiesRepository extends JpaRepository<RoomAdditionalFacilities,Long> {

}
