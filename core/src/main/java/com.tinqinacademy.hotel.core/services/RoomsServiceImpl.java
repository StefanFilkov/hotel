package com.tinqinacademy.hotel.core.services;


import com.tinqinacademy.hotel.api.models.enums.BathroomTypes;
import com.tinqinacademy.hotel.api.models.enums.BedTypes;
import com.tinqinacademy.hotel.api.models.outputs.RoomOutput;
import com.tinqinacademy.hotel.api.operations.addroom.RoomInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.BookRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOutput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomInput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomOutput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
 public class RoomsServiceImpl implements RoomsService {

    public RoomsServiceImpl() {
    }

    @Override
    public String bookRoom() {
        log.info("Start of bookRoom");
        log.info("End of bookRoom");
        return "Room booked successfully";
    }


    @Override
    public RoomOutput addRoom(RoomInput input) {
        log.info("Start addRoom input: {}", input.toString());
        RoomOutput result = RoomOutput
                .builder()
                .id(input.getId())
                .number(input.getNumber())
                .bathroomTypes(BathroomTypes.getByCode(input.getBathroomTypes()))
                .floor(input.getFloor())
                .bedCount(input.getBedCount())
                .price(input.getPrice())
                .bedTypes(BedTypes.getByCode(input.getBedTypes()))
                .build();
        log.info("End of addRoom result: {}", result.toString());
        return result;

    }


    @Override
    public Boolean checkRoomAvailability(){
        log.info("Start of checkRoomAvailability");
        log.info("End of checkRoomAvailability");
        return true;
    }

    @Override
    public String removeRoom(){
        log.info("Start of removeRoom");
        log.info("End of removeRoom");
        return "Room deleted successfully";
    }

    @Override
    public String editRoom(){
        log.info("Start of editRoom");
        log.info("End of editRoom");
        return "Room edited successfully";
    }

    @Override
    public GetRoomOutput getRoom(GetRoomInput input) {
        log.info("Start getRoom input: {}", input.toString());
        GetRoomOutput result = GetRoomOutput.builder().ids(new ArrayList<>()).build();
        log.info("End of getRoom result: {}", result.toString());
        return result;
    }

    @Override
    public GetRoomByIdOutput getRoomById(GetRoomByIdInput input) {
        log.info("Start getRoomById input: {}", input.toString());
        GetRoomByIdOutput result = GetRoomByIdOutput.builder().build();
//        GetRoomByIdOutput result = GetRoomByIdOutput
//                .builder()
//                .room(RoomOutput
//                        .builder()
//                        .price(BigDecimal.valueOf(12.4))
//                        .build())
//                .datesOccupied(new ArrayList<>(List.of(LocalDate.MAX)))
//                .build();
        log.info("End of getRoomById result: {}", result.toString());
        return result;
    }

    @Override
    public void bookRoomById(BookRoomByIdInput input) {
        log.info("Start of bookRoomById");
        RoomOutput.builder()
                .id(input.getId())
                .build();
        log.info("End of bookRoomById");
    }

    @Override
    public DeleteBookingByIdOutput deleteBooking(String id) {
        log.info("Start deleteBooking with id: {}", id);

        DeleteBookingByIdOutput result = DeleteBookingByIdOutput.builder().build();
        log.info("End of deleteBooking result: {}", result.toString());
        return result;
    }

}
