package com.tinqinacademy.hotel.core.services;
import com.tinqinacademy.hotel.api.operations.bookroombyid.BookRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOutput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOutput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomInput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomOutput;
import com.tinqinacademy.hotel.api.operations.addroom.RoomInput;
import com.tinqinacademy.hotel.api.models.outputs.RoomOutput;


public interface RoomsService {
    String bookRoom();
    RoomOutput addRoom(RoomInput input);
    Boolean checkRoomAvailability();
    String removeRoom();
    String editRoom();
    GetRoomOutput getRoom(GetRoomInput input);
    GetRoomByIdOutput getRoomById(GetRoomByIdInput input);
    void bookRoomById(BookRoomByIdInput input);
    DeleteBookingByIdOutput deleteBooking(String id);
}
