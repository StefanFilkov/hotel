package com.tinqinacademy.hotel.api.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum BedTypes {

        SINGLE("single"),
        DOUBLE("double"),
        SMALL_DOUBLE("smallDouble"),
        QUEEN_SIZE("queenSize"),
        KING_SIZE("kingSize"),
        UNKNOWN("");


        String code;
        BedTypes(String name) {
                this.code = name;
        }

        @JsonValue
        @Override
        public String toString(){
                return code;
        }
        @JsonCreator
        public static BedTypes getByCode(String name){
                return Arrays.stream(BedTypes.values())
                        .filter(bedType -> bedType.code.equalsIgnoreCase(name))
                        .findFirst()
                        .orElse(UNKNOWN);
        }
 }
