package com.tinqinacademy.hotel.core.services;

import com.tinqinacademy.hotel.api.models.inputs.GuestInput;
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
import com.tinqinacademy.hotel.persistence.entities.Guest;
import com.tinqinacademy.hotel.persistence.entities.Room;
import com.tinqinacademy.hotel.persistence.models.enums.BathroomTypes;
import com.tinqinacademy.hotel.persistence.models.enums.BedSize;
import com.tinqinacademy.hotel.persistence.repository.BedRepository;
import com.tinqinacademy.hotel.persistence.repository.GuestRepository;
import com.tinqinacademy.hotel.persistence.repository.ReservationRepository;
import com.tinqinacademy.hotel.persistence.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.tinqinacademy.hotel.persistence.entities.Bed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SystemServiceImpl implements SystemService {
    private final ConversionService conversionService;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final BedRepository bedRepository;
    private final RoomRepository roomRepository;

    public SystemServiceImpl(ConversionService conversionService, GuestRepository guestRepository, ReservationRepository reservationRepository, BedRepository bedRepository, RoomRepository roomRepository) {
        this.conversionService = conversionService;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.bedRepository = bedRepository;
        this.roomRepository = roomRepository;
    }


    @Override
    public AddGuestsOutput addGuests(AddGuestInput input) {
        log.info("Start registerUser input: {}", input.toString());

        List<Guest> unknownGuests = saveUnknownGuests(input.getGuests());

        AddGuestsOutput result = AddGuestsOutput
                .builder()
                .guests(unknownGuests.stream().map(guest -> conversionService.convert(guest,GuestInput.class)).toList())
                .build();

        log.info("End of registerUser result: {}", result.toString());
        return result;
    }

    private List<Guest> saveUnknownGuests(List<GuestInput> guestList) {
        log.info("Start saveUnknownGuests input: {}", guestList.toString());

        List<String> inputGuestCardNumbers = guestList.stream().map(GuestInput::getCardNumber).toList();

        List<Guest> existingGuests = guestRepository.findAllByCardNumberIn(new ArrayList<>(inputGuestCardNumbers));

        Set<String> existingGuestsCardNumbers = existingGuests
                .stream()
                .map(Guest::getCardNumber)
                .collect(Collectors.toSet());


        List<Guest> unknownGuests = guestList
                .stream()
                .filter(guestInput -> !existingGuestsCardNumbers.contains(guestInput.getCardNumber()))
                .map(guestInput -> conversionService.convert(guestInput, Guest.class))
                .toList();



        List<Guest> result = new ArrayList<>(existingGuests);

        List<Guest> savedUnknownGuests = guestRepository.saveAll(unknownGuests);

        result.addAll(savedUnknownGuests);

        log.info("End of saveUnknownGuests result: {}", result.toString());
        return result;
    }



    @Override
    public GetRegistrationOutput getRegisteredUser(GetRegistrationInput input) {
        log.info("Start getRegisteredUser input: {}", input.toString());

            //TODO criteria builder

        GetRegistrationOutput result = GetRegistrationOutput
                .builder()
                .firstName(input.getFirstName())
                .lastName(input.getLastName())

                .startDate(input.getStartDate())
                .cardIdN(input.getCardIdN())
                .cardIssueAuthority(input.getCardIssueAuthority())
                .cardIssueDate(input.getCardIssueDate())
                .cardValidityDate(input.getCardValidityDate())
                .phoneN(input.getPhoneN())
                .endDate(input.getEndDate())
                .build();
        log.info("End of getRegisteredUser result: {}", result.toString());
        return result;

    }

    @Override
    public CreateRoomOutput createRoom(CreateRoomInput input) {
        log.info("Start createRoom input: {}", input.toString());


        List<Bed> bedsFinal = new ArrayList<>(input.getBeds()
                .stream()
                .map(
                        inputBedStringType -> bedRepository.findByType(BedSize.
                                getByCode(inputBedStringType)).get()).toList());

        input.getBeds().forEach(inputBedStringType -> bedRepository.findByType(BedSize.getByCode(inputBedStringType)));

        Room save = Room
                .builder()
                .roomNumber(input.getRoomN())
                .roomFloor(input.getFloor())
                .roomBathroomType(BathroomTypes.getByCode(input.getBathroomType()))
                .roomPrice(input.getPrice())
                .bedSizes(bedsFinal)
                .build();

        roomRepository.save(save);

        CreateRoomOutput result = conversionService.convert(save, CreateRoomOutput.class);

        log.info("End of createRoom result: {}", result.toString());
        return result;
    }

    @Override
    public EditRoomOutput editRoom(EditRoomInput input) {
        log.info("Start editRoom input: {}", input.toString());
//        Rooms room = Rooms
//                .builder()
//                .room_id(input.getId())
//                .room_floor(input.getFloor())
//                .room_number(input.getRoomN())
//                .room_price(input.getPrice())
//                .room_bathroom_types(BathroomTypes.getByCode(input.getBathroomType().toString()))
//                .build();
//
//        List<Bed> newBeds = new ArrayList<>();
//        input.getBeds().forEach(bed -> newBeds.add(Bed.getByCode(bed.toString())));
//
//        roomsRepository.putRoom(room,newBeds);
//
//

        EditRoomOutput result = EditRoomOutput.builder().build();
        log.info("End of editRoom result: {}", result.toString());
        return result;
    }

    @Override
    public UpdateRoomOutput updateRoom(UpdateRoomInput input) {
        log.info("Start updateRoom input: {}", input.toString());
        UpdateRoomOutput result = UpdateRoomOutput.builder().build();

       // roomsRepository.patchRoom();







        log.info("End of updateRoom result: {}", result.toString());
        return result;
    }

    @Override
    public DeleteRoomOutput deleteRoom(DeleteRoomInput input) {
        log.info("Start deleteRoom input: {}", input.toString());
        DeleteRoomOutput result = DeleteRoomOutput.builder().build();
        log.info("End of deleteRoom result: {}", result.toString());
        return result;
    }

}