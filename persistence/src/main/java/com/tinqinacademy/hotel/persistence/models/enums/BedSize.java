package com.tinqinacademy.hotel.persistence.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

public enum BedSize {

        SINGLE("single",1),
        DOUBLE("double",2),
        SMALL_DOUBLE("smallDouble",2),
        QUEEN_SIZE("queenSize",3),
        KING_SIZE("kingSize",3),
        UNKNOWN("",0);


        @Getter
        private final String code;
        @Getter
        private final Integer size;

        BedSize(String name, Integer size) {
                this.code = name;
            this.size = size;
        }

        @JsonValue
        @Override
        public String toString(){
                return code;
        }

    public Integer getCapacity(){return size;}

        @JsonCreator
        public static BedSize getByCode(String name){
                return Arrays.stream(BedSize.values())
                        .filter(bedType -> bedType.code.equalsIgnoreCase(name))
                        .findFirst()
                        .orElse(UNKNOWN);
        }
 }
