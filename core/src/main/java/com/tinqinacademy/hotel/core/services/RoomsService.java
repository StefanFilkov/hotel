package com.tinqinacademy.hotel.core.services;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdOutput;
import com.tinqinacademy.hotel.api.operations.checkroomavailability.CheckRoomAvailabilityOutput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdInput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOutput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOutput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOutput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomInput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomOutput;
import com.tinqinacademy.hotel.api.operations.addroom.RoomInput;
import com.tinqinacademy.hotel.api.models.outputs.RoomOutput;

import java.util.UUID;


public interface RoomsService {

    ReserveRoomByIdOutput reserveRoom(ReserveRoomByIdInput input);
    RoomOutput addRoom(RoomInput input);
    CheckRoomAvailabilityOutput checkRoomAvailability();

    DeleteRoomOutput removeRoom();
    EditRoomOutput editRoom();
    GetRoomOutput getRoom(GetRoomInput input);

    GetRoomByIdOutput getRoomById(GetRoomByIdInput input);
    DeleteBookingByIdOutput deleteBooking(DeleteBookingByIdInput id);

}
