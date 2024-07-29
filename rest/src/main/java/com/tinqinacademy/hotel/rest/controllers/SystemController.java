package com.tinqinacademy.hotel.rest.controllers;

import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomInput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOutput;
import com.tinqinacademy.hotel.api.operations.getregistrations.GetRegistrationInput;
import com.tinqinacademy.hotel.api.operations.getregistrations.GetRegistrationOutput;
import com.tinqinacademy.hotel.api.operations.registeruser.AddGuestInput;
import com.tinqinacademy.hotel.api.operations.registeruser.AddGuestsOutput;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomOutput;
import com.tinqinacademy.hotel.core.services.SystemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService){
        this.systemService = systemService;
    }

    @PostMapping("/register")
    public ResponseEntity<AddGuestsOutput> registerUser(@RequestBody AddGuestInput input){
        return new ResponseEntity<>(systemService.addGuests(input), HttpStatus.OK);

    }

    @GetMapping("/register")
    public ResponseEntity<GetRegistrationOutput> getRegisteredUser(@RequestParam LocalDate startDate,
                                                                   @RequestParam String cardIdN,
                                                                   @RequestParam LocalDate cardIssueDate,
                                                                   @RequestParam String cardIssueAuth,
                                                                   @RequestParam LocalDate cardValidityDate,
                                                                   @RequestParam String lastName,
                                                                   @RequestParam String firstName,
                                                                   @RequestParam String phoneN,
                                                                   @RequestParam LocalDate endDate,
                                                                   @RequestParam(value= "data") List<String> data
                                                                   ){
        GetRegistrationInput input = GetRegistrationInput
                .builder()
                .startDate(startDate)
                .cardIdN(cardIdN)
                .cardIssueDate(cardIssueDate)
                .cardIssueAuthority(cardIssueAuth)
                .cardValidityDate(cardValidityDate)
                .lastName(lastName)
                .firstName(firstName)
                .phoneN(phoneN)
                .endDate(endDate)
                .data(data)
                .build();
        return new ResponseEntity<>(systemService.getRegisteredUser(input),HttpStatus.OK);
    }

    @PostMapping("/room")
    public ResponseEntity<CreateRoomOutput> createRoom(@RequestBody CreateRoomInput input){
        return new ResponseEntity<>(systemService.createRoom(input),HttpStatus.OK);
    }

    @PutMapping("/room/{roomId}")
    public ResponseEntity<EditRoomOutput> editRoom(@RequestBody @Valid EditRoomInput input, @PathVariable String roomId){
        input.setId(roomId);
        return new ResponseEntity<>(systemService.editRoom(input),HttpStatus.OK);
    }

    @PatchMapping("/room/{roomId}")
    public ResponseEntity<UpdateRoomOutput> editRoom(@RequestBody UpdateRoomInput input, @PathVariable String roomId){
        return new ResponseEntity<>(systemService.updateRoom(input),HttpStatus.OK);
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseEntity<DeleteRoomOutput> deleteRoom(@PathVariable String roomId){
        DeleteRoomInput input = DeleteRoomInput.builder().id(roomId).build();
        return new ResponseEntity<>(systemService.deleteRoom(input), HttpStatus.OK);
    }
}
