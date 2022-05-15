package com.sai.numberPlate.repository;

import com.sai.numberPlate.Entity.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDetailsRepo extends JpaRepository<VehicleDetails,Integer> {
}
