package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestReservation {

    private Reservation reservation;
    private Customer c1;

    @BeforeEach
    public void setUp() {
        c1 = new Customer("Shirley", "shirley@example.com");
        reservation = new Reservation(c1, LocalDate.of(2024, 11, 15), LocalTime.of(19, 0), 4);
    }

    @Test
    public void testConstructor() {
        assertEquals(LocalDate.of(2024, 11, 15), reservation.getReservationDate());
        assertEquals(LocalTime.of(19, 0), reservation.getReservationTime());
        assertEquals(4, reservation.getNumberOfGuests());
        assertEquals(c1, reservation.getCustomer());
        assertTrue(reservation.getIsReserved());
    }

    @Test
    public void testModifyReservation() {
        // Modify reservation with new LocalDate and LocalTime
        reservation.modifyReservation(LocalDate.of(2024, 11, 16), LocalTime.of(20, 0), 5);
        assertEquals(LocalDate.of(2024, 11, 16), reservation.getReservationDate());
        assertEquals(LocalTime.of(20, 0), reservation.getReservationTime());
        assertEquals(5, reservation.getNumberOfGuests());
    }

    @Test
    public void testCancelReservation() {
        reservation.cancelReservation();
        assertFalse(reservation.getIsReserved());
    }

    @Test
    public void testGetters() {
        assertEquals(LocalDate.of(2024, 11, 15), reservation.getReservationDate());
        assertEquals(LocalTime.of(19, 0), reservation.getReservationTime());
        assertEquals(4, reservation.getNumberOfGuests());
        assertEquals(c1, reservation.getCustomer());
        assertTrue(reservation.getIsReserved());
    }

    @Test
    public void testSetters() {
        Customer c2 = new Customer("Marco", "marco@example.com");
        reservation.setReservationDate(LocalDate.of(2024, 11, 17));
        reservation.setReservationTime(LocalTime.of(21, 0));
        reservation.setNumberOfGuests(6);
        reservation.setCustomer(c2);
        reservation.setIsReserved(true);

        assertEquals(LocalDate.of(2024, 11, 17), reservation.getReservationDate());
        assertEquals(LocalTime.of(21, 0), reservation.getReservationTime());
        assertEquals(6, reservation.getNumberOfGuests());
        assertEquals(c2, reservation.getCustomer());
        assertTrue(reservation.getIsReserved());
    }
}
