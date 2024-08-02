package com.tinqinacademy.hotel.rest.controllers;

import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdOutput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdInput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOutput;
import com.tinqinacademy.hotel.api.operations.getfreerooms.GetFreeRoomsInput;
import com.tinqinacademy.hotel.api.operations.getfreerooms.GetFreeRoomsOutput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOutput;
import com.tinqinacademy.hotel.core.processors.hotel.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class RoomsController extends BaseController {

    private final GetFreeRoomsOperationProcessor getFreeRoomsOperationProcessor;
    private final ReserveRoomOperationProcessor reserveRoomOperationProcessor;
    private final GetRoomByIdOperationProcessor getRoomByIdOperationProcessor;
    private final DeleteBookingOperationProcessor deleteBookingOperationProcessor;

    public RoomsController(GetFreeRoomsOperationProcessor getFreeRoomsOperationProcessor, ReserveRoomOperationProcessor reserveRoomOperationProcessor, GetRoomByIdOperationProcessor getRoomByIdOperationProcessor, DeleteBookingOperationProcessor deleteBookingOperationProcessor) {
        this.getFreeRoomsOperationProcessor = getFreeRoomsOperationProcessor;
        this.reserveRoomOperationProcessor = reserveRoomOperationProcessor;
        this.getRoomByIdOperationProcessor = getRoomByIdOperationProcessor;
        this.deleteBookingOperationProcessor = deleteBookingOperationProcessor;
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
    public ResponseEntity<?> bookRoom(@RequestBody ReserveRoomByIdInput input) {
        Either<Errors, ReserveRoomByIdOutput> result = reserveRoomOperationProcessor.process(input);
        return handleResult(result);
    }


    @GetMapping(URLMappings.GET_ROOM)
    public ResponseEntity<?> getRooms(@RequestParam LocalDate startDate,
                                      @RequestParam LocalDate endDate,
                                      @RequestParam List<String> BedTypes,
                                      @RequestParam String bathroomType) {
        GetFreeRoomsInput input = GetFreeRoomsInput
                .builder()
                .endDate(endDate)
                .bathroomTypes(bathroomType)
                .startDate(startDate)
                .bedSizes(BedTypes)
                .build();

        Either<Errors, GetFreeRoomsOutput> result = getFreeRoomsOperationProcessor.process(input);
        return handleResult(result);

    }


    @GetMapping(URLMappings.GET_ROOM_BY_ID)
    public ResponseEntity<?> getRoomById(@PathVariable String roomId) {
        GetRoomByIdInput roomsServiceRoomById = GetRoomByIdInput
                .builder()
                .id(String.valueOf(roomId))
                .build();

        Either<Errors, GetRoomByIdOutput> result = getRoomByIdOperationProcessor.process(roomsServiceRoomById);

        return handleResult(result);
    }

    @DeleteMapping(URLMappings.DELETE_BOOKING_BY_ID)
    public ResponseEntity<?> deleteBookingById(@PathVariable String bookingId) {
        DeleteBookingByIdInput input = DeleteBookingByIdInput
                .builder()
                .id(bookingId)
                .build();
        Either<Errors, DeleteBookingByIdOutput> result = deleteBookingOperationProcessor.process(input);
        return handleResult(result);

    }
}
