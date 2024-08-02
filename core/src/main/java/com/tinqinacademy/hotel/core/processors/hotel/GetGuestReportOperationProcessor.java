package com.tinqinacademy.hotel.core.processors.hotel;

import com.tinqinacademy.hotel.api.errors.Errors;
import com.tinqinacademy.hotel.api.models.outputs.GuestOutput;
import com.tinqinacademy.hotel.api.operations.getguestreport.GetGuestReportInput;
import com.tinqinacademy.hotel.api.operations.getguestreport.GetGuestReportOperation;
import com.tinqinacademy.hotel.api.operations.getguestreport.GetGuestReportOutput;
import com.tinqinacademy.hotel.core.errorsmapper.ErrorMapper;
import com.tinqinacademy.hotel.core.processors.BaseOperationProcessor;
import com.tinqinacademy.hotel.persistence.entities.Guest;
import com.tinqinacademy.hotel.persistence.repository.GuestRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.tinqinacademy.hotel.persistence.specifications.GuestSpecification.*;
import static io.vavr.API.*;

@Service
@Slf4j
public class GetGuestReportOperationProcessor extends BaseOperationProcessor implements GetGuestReportOperation {
    private final GuestRepository guestRepository;

    protected GetGuestReportOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, GuestRepository guestRepository) {
        super(conversionService, validator, errorMapper);
        this.guestRepository = guestRepository;
    }

    @Override
    public Either<Errors, GetGuestReportOutput> process(GetGuestReportInput input) {
        return validateInput(input).flatMap(validated -> getGuestReport(input));

    }
    private Either<Errors, GetGuestReportOutput> getGuestReport(GetGuestReportInput input) {
        return Try.of(() -> {
                    log.info("Start getGuestReport input: {}", input.toString());

                    Set<Guest> allGuestsInTimePeriod = new HashSet<>(guestRepository.findByStartDateAndEndDate(input.getStartDate(), input.getEndDate()));

                    List<Specification<Guest>> specifications = new ArrayList<>();
                    specifications.add(hasFirstName(input.getFirstName()));
                    specifications.add(hasLastName(input.getLastName()));
                    specifications.add(hasCardNumber(input.getCardIdN()));
                    specifications.add(hasCardIssueAuthority(input.getCardIssueAuthority()));
                    specifications.add(hasCardValidity(input.getCardValidityDate()));
                    specifications.add(hasCardIssueDate(input.getCardIssueDate()));
                    specifications.add(hasBirthDate(input.getBirthdate()));

                    List<Specification<Guest>> enteredFields = specifications.stream().filter(Objects::nonNull).toList();

                    Specification<Guest> finalSpecification = specificationBuilder(enteredFields);

                    Set<Guest> filteredGuests = new HashSet<>(guestRepository.findAll(finalSpecification));

                    List<Guest> resultGuests = allGuestsInTimePeriod.stream()
                            .filter(filteredGuests::contains)
                            .toList();

                    GetGuestReportOutput result = GetGuestReportOutput
                            .builder()
                            .data(resultGuests.stream().map(request -> conversionService.convert(request, GuestOutput.class)).toList())
                            .build();

                    log.info("End of getGuestReport result: {}", result.toString());
                    return result;
                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }

    private Specification<Guest> specificationBuilder(List<Specification<Guest>> specifications) {
        Specification<Guest> result = specifications.get(0);
        for (int i = 1; i < specifications.size(); i++) {
            result = result.and(specifications.get(i));
        }
        return result;
    }
}
