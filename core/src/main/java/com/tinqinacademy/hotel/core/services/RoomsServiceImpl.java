package com.tinqinacademy.hotel.core.services;


import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
import com.tinqinacademy.hotel.api.models.outputs.RoomOutput;
import com.tinqinacademy.hotel.api.operations.addroom.RoomInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdOutput;
import com.tinqinacademy.hotel.api.operations.checkroomavailability.CheckRoomAvailabilityOutput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOutput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOutput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomInput;
import com.tinqinacademy.hotel.api.operations.getroom.GetRoomOutput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOutput;
import com.tinqinacademy.hotel.core.exceptions.RoomNotFoundException;
import com.tinqinacademy.hotel.core.exceptions.UserNotFoundException;
import com.tinqinacademy.hotel.persistence.entities.*;
import com.tinqinacademy.hotel.persistence.models.enums.BedSize;
import com.tinqinacademy.hotel.persistence.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class RoomsServiceImpl implements RoomsService {

    private final RoomRepository roomRepository;
    private final ConversionService conversionService;
    private final BedRepository bedRepository;
    private final GuestRepository guestRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public RoomsServiceImpl(RoomRepository roomRepository, ConversionService conversionService, BedRepository bedRepository, GuestRepository guestRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.conversionService = conversionService;
        this.bedRepository = bedRepository;
        this.guestRepository = guestRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReserveRoomByIdOutput reserveRoom(ReserveRoomByIdInput input) {
        log.info("Start of bookRoom");


        List<Guest> guests = getGuests(input.getGuestList());

        Room room = roomRepository.findById(input.getRoomId()).orElseThrow();

        if (ObjectUtils.isEmpty(room)) {
            throw new RoomNotFoundException("Room not found");
        }

        User user = userRepository.findByPhone(input.getUserInput().getPhone()).orElse(null);

        if (ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException("User not found");
        }

        Reservation build = conversionService.convert(input, Reservation.ReservationBuilder.class)
                .guests(guests)
                .room(room)
                .user(user)
                .build();

        Reservation save = reservationRepository.save(build);


        //Room rooms = roomRepository.findById(input.getRoomId()).orElseThrow(() -> new RoomNotFoundException("Room not found"));


        log.info("End of bookRoom");
        return ReserveRoomByIdOutput.builder().build();
    }

    private List<Guest> getGuests(List<GuestInput> guestList) {

        Set<String> existingGuestsCardNumbers = guestList
                .stream()
                .map(GuestInput::getCardNumber)
                .collect(Collectors.toSet());

        List<Guest> existingGuests = guestRepository.findAllByCardNumberIn(new ArrayList<>(existingGuestsCardNumbers));

        List<Guest> unknownGuests = guestList
                .stream()
                .filter(guestInput -> !existingGuestsCardNumbers.contains(guestInput.getCardNumber()))
                .map(guestInput -> conversionService.convert(guestInput, Guest.class))
                .toList();

        List<Guest> result = new ArrayList<>(existingGuests);
        List<Guest> savedUnknownGuests = guestRepository.saveAll(unknownGuests);
        result.addAll(savedUnknownGuests);

        return result;
    }

    @Override
    public RoomOutput addRoom(RoomInput input) {
        log.info("Start addRoom input: {}", input.toString());

        List<BedSize> bedSizeList = input.getBedTypes().stream().map(BedSize::getByCode).toList();

        List<Bed> beds = bedRepository.findAll().stream().filter(bed -> bedSizeList.contains(bed.getType())).toList();

        Room room = conversionService.convert(input, Room.RoomBuilder.class).bedSizes(beds).build();
        roomRepository.save(room);
        RoomOutput result = conversionService.convert(room, RoomOutput.class);
        log.info("End of addRoom result: {}", result.toString());
        return result;

    }


    @Override
    public CheckRoomAvailabilityOutput checkRoomAvailability() {
        log.info("Start of checkRoomAvailability");
        log.info("End of checkRoomAvailability");
        return CheckRoomAvailabilityOutput.builder().build();
    }

    @Override
    public DeleteRoomOutput removeRoom() {
        log.info("Start of removeRoom");
        log.info("End of removeRoom");
        return DeleteRoomOutput.builder().build();
    }

    @Override
    public EditRoomOutput editRoom() {
        log.info("Start of editRoom");
        log.info("End of editRoom");
        return EditRoomOutput.builder().build();
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
        UUID id = input.getId();

        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        List<Reservation> reservations = reservationRepository.findAllByRoomId(id);
        List<LocalDate> datesOccupied = reservations
                .stream()
                .flatMap(reservation -> getDatesOccupied(reservation).stream())
                .toList();

        GetRoomByIdOutput result = conversionService.convert(room, GetRoomByIdOutput.GetRoomByIdOutputBuilder.class)
                .datesOccupied(datesOccupied)
                .build();


        log.info("End of getRoomById result: {}", result.toString());
        return result;
    }

    private List<LocalDate> getDatesOccupied(Reservation reservations) {

        LocalDate startdate = reservations.getStartDate();
        List<LocalDate> result = new ArrayList<>();

        while (startdate.isBefore(reservations.getEndDate())) {
            result.add(startdate);
            startdate = startdate.plusDays(1);
        }

        return result;
    }

    @Override
    public DeleteBookingByIdOutput deleteBooking(String id) {
        log.info("Start deleteBooking with id: {}", id);

        DeleteBookingByIdOutput result = DeleteBookingByIdOutput.builder().build();

        log.info("End of deleteBooking result: {}", result.toString());
        return result;
    }

}
