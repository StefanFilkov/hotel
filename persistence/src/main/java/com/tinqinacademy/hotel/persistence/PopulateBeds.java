package com.tinqinacademy.hotel.persistence;


import com.tinqinacademy.hotel.persistence.entities.Bed;
import com.tinqinacademy.hotel.persistence.models.enums.BedSize;
import com.tinqinacademy.hotel.persistence.repository.BedRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PopulateBeds implements ApplicationRunner {
    private final BedRepository bedRepository;

    public PopulateBeds(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<BedSize> bedSizeList = new ArrayList<>(Arrays.stream(BedSize.values()).toList());
        List<BedSize> savedBedSize = bedRepository.findAll().stream().map(Bed::getType).toList();


        if (bedSizeList.size() > savedBedSize.size()) {
            bedSizeList.removeAll(savedBedSize);
            bedRepository.saveAll(bedSizeList.stream().map(bedSize -> Bed
                    .builder()
                    .type(bedSize)
                    .capacity(bedSize.getCapacity())
                    .build())
                    .toList());


        } else if (bedSizeList.size() < savedBedSize.size()) {
            savedBedSize.removeAll(bedSizeList);
            bedRepository.saveAll(bedSizeList.stream().map(bedSize -> Bed
                    .builder()
                    .type(bedSize)
                    .capacity(bedSize.getCapacity())
                    .build())
                    .toList());
            }
        }

    }

