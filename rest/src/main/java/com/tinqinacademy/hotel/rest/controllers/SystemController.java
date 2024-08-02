package com.tinqinacademy.hotel.rest.controllers;

import com.tinqinacademy.hotel.api.errors.Errors;
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
import com.tinqinacademy.hotel.core.processors.hotel.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class SystemController extends BaseController {

    private final AddGuestsOperationProcessor addGuestsOperationProcessor;
    private final GetGuestReportOperationProcessor getGuestReportOperationProcessor;
    private final CreateRoomOperationProcessor createRoomOperationProcessor;
    private final EditRoomOperationProcessor editRoomOperationProcessor;
    private final UpdateRoomOperationProcessor updateRoomOperationProcessor;
    private final DeleteBookingOperationProcessor deleteBookingOperationProcessor;
    private final RemoveRoomOperationProcessor removeRoomOperationProcessor;

    public SystemController(AddGuestsOperationProcessor addGuestsOperationProcessor, GetGuestReportOperationProcessor getGuestReportOperationProcessor, CreateRoomOperationProcessor createRoomOperationProcessor, EditRoomOperationProcessor editRoomOperationProcessor, UpdateRoomOperationProcessor updateRoomOperationProcessor, DeleteBookingOperationProcessor deleteBookingOperationProcessor, RemoveRoomOperationProcessor removeRoomOperationProcessor) {
        super();
        this.addGuestsOperationProcessor = addGuestsOperationProcessor;
        this.getGuestReportOperationProcessor = getGuestReportOperationProcessor;
        this.createRoomOperationProcessor = createRoomOperationProcessor;
        this.editRoomOperationProcessor = editRoomOperationProcessor;
        this.updateRoomOperationProcessor = updateRoomOperationProcessor;
        this.deleteBookingOperationProcessor = deleteBookingOperationProcessor;
        this.removeRoomOperationProcessor = removeRoomOperationProcessor;
    }

    @PostMapping(URLMappings.POST_REGISTER_VISITOR)
    public ResponseEntity<?> registerVisitors(@RequestBody AddGuestInput input) {
        Either<Errors, AddGuestsOutput> result = addGuestsOperationProcessor.process(input);

        return handleResult(result);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @Operation(
            summary = "returns a report based on input",
            description = "Implemented options:"
    )
    @GetMapping(URLMappings.GET_REPORT)
    public ResponseEntity<?> getRegisteredUser(@RequestParam LocalDate startDate,
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
        Either<Errors, GetGuestReportOutput> result = getGuestReportOperationProcessor.process(input);

        return handleResult(result);
    }

    @PostMapping(URLMappings.POST_CREATE_ROOM)
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomInput input) {
        Either<Errors, CreateRoomOutput> result = createRoomOperationProcessor.process(input);
        return handleResult(result);
    }

    @PutMapping(URLMappings.PUT_UPDATE_ROOM)
    public ResponseEntity<?> editRoom(@RequestBody @Valid EditRoomInput input, @PathVariable String roomId) {
        input.setId(roomId);
        Either<Errors, EditRoomOutput> result = editRoomOperationProcessor.process(input);
        return handleResult(result);
    }

    @PatchMapping(URLMappings.PATCH_EDIT_ROOM)
    public ResponseEntity<?> editRoom(@RequestBody UpdateRoomInput input, @PathVariable String roomId) {
        input.setId(roomId);
        Either<Errors, UpdateRoomOutput> result = updateRoomOperationProcessor.process(input);
        return handleResult(result);
    }

    @DeleteMapping(URLMappings.DELETE_ROOM)
    public ResponseEntity<?> deleteRoom(@PathVariable String roomId) {
        DeleteRoomInput input = DeleteRoomInput.builder().id(roomId).build();
        Either<Errors, DeleteRoomOutput> result = removeRoomOperationProcessor.process(input);
        return handleResult(result);
    }
}
