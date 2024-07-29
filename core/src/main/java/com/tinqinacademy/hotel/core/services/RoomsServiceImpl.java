package com.tinqinacademy.hotel.core.services;


import com.tinqinacademy.hotel.api.exceptions.BedTypeNotValidException;
import com.tinqinacademy.hotel.api.exceptions.InvalidBathroomTypeException;
import com.tinqinacademy.hotel.api.exceptions.RoomNotFoundException;
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
import com.tinqinacademy.hotel.persistence.entities.Bed;
import com.tinqinacademy.hotel.persistence.entities.Reservation;
import com.tinqinacademy.hotel.persistence.entities.Room;
import com.tinqinacademy.hotel.persistence.models.enums.BathroomTypes;
import com.tinqinacademy.hotel.persistence.models.enums.BedSize;
import com.tinqinacademy.hotel.persistence.repository.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

        Room room = roomRepository.findById(UUID.fromString(input.getRoomId())).orElseThrow(() -> new RoomNotFoundException("Room not found"));


        Reservation build = conversionService.convert(input, Reservation.ReservationBuilder.class)
                .fullPrice(calculateFullPrice(room, input.getStartDate(), input.getEndDate()))
                .room(room)
                .build();

        Reservation save = reservationRepository.save(build);


        log.info("End of bookRoom");
        return ReserveRoomByIdOutput
                .builder()
                .build();
    }

    private BigDecimal calculateFullPrice(Room room, LocalDate startDate, LocalDate endDate) {
        log.info("Start addRoom input: room: {}; StartDate: {}; EndDate: {}", room, startDate, endDate);

        List<LocalDate> dates = getDatesOccupied(Reservation
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .build());

        BigDecimal result = room.getRoomPrice().multiply(new BigDecimal(dates.size()));

        log.info("End of calculateFullPrice result: {}", result);
        return result;

    }

    @Override
    public RoomOutput addRoom(RoomInput input) {
        log.info("Start addRoom input: {}", input.toString());

        validateRoomInput(input);
        List<Bed> beds = getBedsByCode(input.getBedTypes());

        Room room = conversionService.convert(input, Room.RoomBuilder.class).bedSizes(beds).build();
        roomRepository.save(room);

        RoomOutput result = conversionService.convert(room, RoomOutput.class);

        log.info("End of addRoom result: {}", result);
        return result;

    }

    private void validateRoomInput(RoomInput input) {
        log.info("Start validateRoomInput input: {}", input);

        String bathroomType = input.getBathroomTypes();
        if (BathroomTypes.UNKNOWN.equals(BathroomTypes.getByCode(bathroomType))) {
            log.error("Invalid bathroom type for input: {}", input);
            throw new InvalidBathroomTypeException();
        }

        List<String> beds = input.getBedTypes();
        beds.stream()
                .filter(bed -> BedSize.UNKNOWN.equals(BedSize.getByCode(bed)))
                .findFirst()
                .ifPresent(bed -> {
                    log.error("Invalid bed types for input: {}", input);
                    throw new BedTypeNotValidException(bed);
                });

        log.info("End validateRoomInput successful");
    }

    private List<Bed> getBedsByCode(List<String> bedTypes) {
        log.info("Start getBedsByCode input: {}", bedTypes);

        List<BedSize> bedSizeList = bedTypes
                .stream()
                .map(BedSize::getByCode)
                .toList();

        List<Bed> beds = bedRepository
                .findAll()
                .stream()
                .filter(bed -> bedSizeList.contains(bed.getType()))
                .toList();

        log.info("End getBedsByCode result: {}", beds);
        return beds;
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
        UUID id = UUID.fromString(input.getId());

        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        List<Reservation> reservations = reservationRepository.findAllByRoomId(id);
        List<LocalDate> datesOccupied = reservations
                .stream()
                .flatMap(reservation -> getDatesOccupied(reservation).stream())
                .toList();

        GetRoomByIdOutput result = conversionService.convert(room, GetRoomByIdOutput.GetRoomByIdOutputBuilder.class)
                .datesOccupied(datesOccupied)
                .id(String.valueOf(room.getId()))
                .price(room.getRoomPrice())
                .build();


        log.info("End of getRoomById result: {}", result.toString());
        return result;
    }

    private List<LocalDate> getDatesOccupied(Reservation reservation) {
        log.info("Start getRoomById reservation: {}", reservation.toString());

        List<LocalDate> result = reservation
                .getStartDate()
                .datesUntil(reservation.getEndDate().plusDays(1))
                .collect(Collectors.toList());

        log.info("End getRoomById result: {}", result);
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
