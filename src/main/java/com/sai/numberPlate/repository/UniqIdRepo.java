package com.sai.numberPlate.repository;

import com.sai.numberPlate.Entity.UniqIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniqIdRepo extends JpaRepository<UniqIdEntity,Integer> {

    public UniqIdEntity findByDeviceId(String id);
}
