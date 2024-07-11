package com.tinqinacademy.hotel.core.services;

import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomInput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOutput;
import com.tinqinacademy.hotel.api.operations.getregistrations.GetRegistrationInput;
import com.tinqinacademy.hotel.api.operations.getregistrations.GetRegistrationOutput;
import com.tinqinacademy.hotel.api.operations.registeruser.RegisterUserInput;
import com.tinqinacademy.hotel.api.operations.registeruser.RegisterUserOutput;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomOutput;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomInput;

public interface SystemService {
    RegisterUserOutput registerUser(RegisterUserInput input);
    GetRegistrationOutput getRegisteredUser(GetRegistrationInput input);
    CreateRoomOutput createRoom(CreateRoomInput input);
    EditRoomOutput editRoom(EditRoomInput input);
    UpdateRoomOutput updateRoom(UpdateRoomInput input);
    DeleteRoomOutput deleteRoom(DeleteRoomInput input);
}
