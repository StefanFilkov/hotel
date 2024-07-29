package com.tinqinacademy.hotel.rest.controllers;

import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomInput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOutput;
import com.tinqinacademy.hotel.api.operations.getguestreport.GetGuestReportInput;
import com.tinqinacademy.hotel.api.operations.getguestreport.GetGuestReportOutput;
import com.tinqinacademy.hotel.api.operations.registeruser.AddGuestInput;
import com.tinqinacademy.hotel.api.operations.registeruser.AddGuestsOutput;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomOutput;
import com.tinqinacademy.hotel.core.services.SystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<AddGuestsOutput> registerUser(@RequestBody AddGuestInput input) {
//        return new ResponseEntity<>(systemService.addGuests(input), HttpStatus.OK);
//
//    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @Operation(
            summary = "returns a report based on input",
            description = "Implemented options:"
    )
    @GetMapping(URLMappings.GET_REPORT)
    public ResponseEntity<GetGuestReportOutput> getRegisteredUser(@RequestParam LocalDate startDate,
                                                                  @RequestParam LocalDate endDate,
                                                                  @RequestParam(required = false) String roomNumber,
                                                                  @RequestParam(required = false) String cardIdN,
                                                                  @RequestParam(required = false) String cardIssueDate,
                                                                  @RequestParam(required = false) String cardIssueAuth,
                                                                  @RequestParam(required = false) String cardValidityDate,
                                                                  @RequestParam(required = false) String lastName,
                                                                  @RequestParam(required = false) String firstName,
                                                                  @RequestParam(required = false) String phoneN,
                                                                  @RequestParam(required = false) String birthdate
    ) {
        GetGuestReportInput input = GetGuestReportInput
                .builder()
                .roomN(roomNumber)
                .startDate(startDate)
                .cardIdN(cardIdN)
                .cardIssueDate(cardIssueDate)
                .cardIssueAuthority(cardIssueAuth)
                .cardValidityDate(cardValidityDate)
                .lastName(lastName)
                .firstName(firstName)
                .phoneN(phoneN)
                .endDate(endDate)
                .birthdate(birthdate)
                .build();
        return new ResponseEntity<>(systemService.getGuestReport(input), HttpStatus.OK);
    }

    @PostMapping(URLMappings.POST_CREATE_ROOM)
    public ResponseEntity<CreateRoomOutput> createRoom(@RequestBody CreateRoomInput input) {
        return new ResponseEntity<>(systemService.createRoom(input), HttpStatus.OK);
    }

    @PutMapping(URLMappings.PUT_UPDATE_ROOM)
    public ResponseEntity<EditRoomOutput> editRoom(@RequestBody @Valid EditRoomInput input, @PathVariable String roomId) {
        input.setId(roomId);
        return new ResponseEntity<>(systemService.editRoom(input), HttpStatus.OK);
    }

//    @PatchMapping("/room/{roomId}")
//    public ResponseEntity<UpdateRoomOutput> editRoom(@RequestBody UpdateRoomInput input, @PathVariable String roomId) {
//        return new ResponseEntity<>(systemService.updateRoom(input), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/room/{roomId}")
//    public ResponseEntity<DeleteRoomOutput> deleteRoom(@PathVariable String roomId) {
//        DeleteRoomInput input = DeleteRoomInput.builder().id(roomId).build();
//        return new ResponseEntity<>(systemService.deleteRoom(input), HttpStatus.OK);
//    }
}
