package com.tinqinacademy.hotel.persistence.specifications;

import com.tinqinacademy.hotel.persistence.entities.Guest;
import org.springframework.data.jpa.domain.Specification;

public class GuestSpecification {

    private static boolean isEmpty(String value) {
        return value != null && !value.isEmpty();
    }
    public static Specification<Guest> hasFirstName(final String firstName) {
        return isEmpty(firstName)
                ? (guest, query, cb) -> cb.equal(guest.get("firstName"), firstName)
                : (root, query, cb) -> cb.conjunction();
    }

    public static Specification<Guest> hasLastName(final String lastName) {
        return isEmpty(lastName)
                ? (guest, cq, cb) -> cb.equal(guest.get("lastName"), lastName)
                : (root, query, cb) -> cb.conjunction();
    }

    public static Specification<Guest> hasCardNumber(final String cardNumber) {
        return isEmpty(cardNumber)
                ? (guest, cq, cb) -> cb.equal(guest.get("cardNumber"), cardNumber)
                : (root, query, cb) -> cb.conjunction();
    }

    public static Specification<Guest> hasCardIssueAuthority(String cardIssueAuthority) {
        return isEmpty(cardIssueAuthority)
                ? (guest, cq, cb) -> cb.equal(guest.get("cardIssueAuthority"), cardIssueAuthority)
                : (root, query, cb) -> cb.conjunction();
    }

    public static Specification<Guest> hasCardValidity(String cardValidity) {
        return isEmpty(cardValidity)
                ? (guest, cq, cb) -> cb.equal(guest.get("cardValidity"), cardValidity)
                : (root, query, cb) -> cb.conjunction();
    }

    public static Specification<Guest> hasCardIssueDate(String cardIssueDate) {
        return isEmpty(cardIssueDate)
                ? (guest, cq, cb) -> cb.equal(guest.get("cardIssueDate"), cardIssueDate)
                : (root, query, cb) -> cb.conjunction();
    }

    public static Specification<Guest> hasBirthDate(String birthDate) {
        return isEmpty(birthDate)
                ? (guest, cq, cb) -> cb.equal(guest.get("birthDate"), birthDate)
                : (root, query, cb) -> cb.conjunction();
    }
}
