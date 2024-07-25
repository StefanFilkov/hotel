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
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomOutput;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    //private final UsersRepository usersRepository;
   // private final  RoomsRepositoryImpl roomsRepository;
   // @Autowired
   // public SystemServiceImpl(UsersRepository usersRepository, RoomsRepositoryImpl roomsRepository) {
   //     this.usersRepository = usersRepository;
   //     this.roomsRepository = roomsRepository;
   // }

    @Override
    public RegisterUserOutput registerUser(RegisterUserInput input) {
        log.info("Start registerUser input: {}", input.toString());
        //usersRepository.save(Users
               // .builder()
                      //  .birth_date(input.getBirthdate())
                      //  .email(input.getEmail())
                      //  .phone(input.getPhoneN())
                      //  .lastName(input.getLastName())
                      //  .firstName(input.getFirstName())
               // .build());

        RegisterUserOutput result = RegisterUserOutput.builder().build();
        log.info("End of registerUser result: {}", result.toString());
        return result;
    }

    @Override
    public GetRegistrationOutput getRegisteredUser(GetRegistrationInput input) {
        log.info("Start getRegisteredUser input: {}", input.toString());
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
//        List<Bed> bedsFinal = new ArrayList<>();
//
//        input.getBeds().forEach(bed -> bedsFinal.add(Bed.getByCode(bed.toString())));
//
//        roomsRepository.save(Rooms
//                .builder()
//                        .room_id(UUID.randomUUID())
//                        .room_floor(input.getFloor())
//                        .room_number(input.getRoomN())
//                        .room_price(input.getPrice())
//                        .room_beds(bedsFinal)
//                        .room_bathroom_types(BathroomTypes.getByCode(input.getBathroomType()))
//                .build());
//
//        CreateRoomOutput result = CreateRoomOutput.builder().build();
//        log.info("End of createRoom result: {}", result.toString());
        return null;
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