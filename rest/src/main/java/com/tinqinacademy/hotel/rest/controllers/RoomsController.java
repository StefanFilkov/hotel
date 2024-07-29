package com.tinqinacademy.hotel.rest.controllers;

import com.tinqinacademy.hotel.api.models.outputs.RoomOutput;
import com.tinqinacademy.hotel.api.operations.addroom.RoomInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdOutput;
import com.tinqinacademy.hotel.api.operations.checkroomavailability.CheckRoomAvailabilityOutput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdInput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOutput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOutput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomInput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomOutput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOutput;
import com.tinqinacademy.hotel.core.services.RoomsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class RoomsController {

    private final RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @Operation(
            summary = "Edits a room",
            description = "Edits a room",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Room does not exist"),
                    @ApiResponse(responseCode = "200", description = "Room edited successfully")
            })
    @PutMapping()
    public ResponseEntity<EditRoomOutput> editRoom() {
        return new ResponseEntity<>(roomsService.editRoom(), HttpStatus.OK);
    }

    @Operation(
            summary = "Removes a room",
            description = "Removes a room",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Room does not exist"),
                    @ApiResponse(responseCode = "200", description = "Room deleted")

            })
    @DeleteMapping
    public ResponseEntity<DeleteRoomOutput> removeRoom() {
        return new ResponseEntity<>(roomsService.removeRoom(), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room Booked successfully"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @Operation(
            summary = "Books a room",
            description = "Books a room"
    )
    @PostMapping(URLMappings.POST_BOOK_ROOM_BY_ID)
    public ResponseEntity<ReserveRoomByIdOutput> bookRoom(@RequestBody ReserveRoomByIdInput input) {
        return new ResponseEntity<>(roomsService.reserveRoom(input), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @Operation(summary = "Checks if room is available", description = "Checks if room is available")
    @GetMapping("/check")
    public ResponseEntity<CheckRoomAvailabilityOutput> checkRoomsAvailability() {
        return new ResponseEntity<>(roomsService.checkRoomAvailability(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @Operation(
            summary = "Adds a room",
            description = "Adds a room"
    )
    @PostMapping
    public ResponseEntity<RoomOutput> addRoom(@RequestBody RoomInput input) {
        RoomOutput result = roomsService.addRoom(input);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetRoomOutput> getRoom(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam Integer bedCount, @RequestParam String bedType, @RequestParam String bathroomType){
        GetRoomInput input= GetRoomInput.builder()
                .endDate(endDate)
                .bathroomTypes(bathroomType)
                .startDate(startDate)
                .bedTypes(bedType)
                .bedCount(bedCount)
                .build();
        return new ResponseEntity<>(roomsService.getRoom(input), HttpStatus.OK);

    }


    @GetMapping(URLMappings.GET_ROOM_BY_ID)
    public ResponseEntity<GetRoomByIdOutput> getRoomById(@PathVariable UUID roomId){
        GetRoomByIdInput roomsServiceRoomById = GetRoomByIdInput.builder().id(String.valueOf(roomId)).build();
        return new ResponseEntity<>(roomsService.getRoomById(roomsServiceRoomById), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> bookRoomById(@PathVariable String id,@RequestBody ReserveRoomByIdInput input){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(URLMappings.DELETE_BOOKING_BY_ID)
    public ResponseEntity<DeleteBookingByIdOutput> deleteBookingById(@PathVariable String bookingId){
        return new ResponseEntity<>(roomsService.deleteBooking(DeleteBookingByIdInput.builder().id(bookingId).build()),HttpStatus.OK);

    }
}
