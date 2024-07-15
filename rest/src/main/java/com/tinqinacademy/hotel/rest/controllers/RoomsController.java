package com.tinqinacademy.hotel.rest.controllers;

import com.tinqinacademy.hotel.api.models.outputs.RoomOutput;
import com.tinqinacademy.hotel.api.operations.addroom.RoomInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.BookRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOutput;
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

@RestController
@RequestMapping("/rooms")
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
    public ResponseEntity<String> editRoom() {
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
    public ResponseEntity<String> removeRoom() {
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
    @PostMapping("/book")
    public ResponseEntity<String> bookRoom() {
        return new ResponseEntity<>(roomsService.bookRoom(), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @Operation(summary = "Checks if room is available", description = "Checks if room is available")
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkRoomsAvailability() {
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
        return new ResponseEntity<>(roomsService.addRoom(input), HttpStatus.CREATED);
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


    @GetMapping("/{id}")
    public ResponseEntity<GetRoomByIdOutput> getRoomById(@PathVariable String id){
        GetRoomByIdInput result = GetRoomByIdInput
                .builder()
                .id(id)
                .build();
        return new ResponseEntity<>(roomsService.getRoomById(result), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> bookRoomById(@PathVariable String id,@RequestBody BookRoomByIdInput input){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<DeleteBookingByIdOutput> deleteBookingById(@PathVariable String bookingId){
        return new ResponseEntity<>(roomsService.deleteBooking(bookingId),HttpStatus.OK);

    }
}
