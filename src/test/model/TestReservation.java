package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestReservation {

    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        reservation = new Reservation("Shirley", "2024-10-15", "19:00", 4);
    }

    @Test
    public void testConstructor() {
        assertEquals("2024-10-15", reservation.getReservationDate());
        assertEquals("19:00", reservation.getReservationTime());
        assertEquals(4, reservation.getNumberOfGuests());
        assertEquals("Shirley", reservation.getCustomerName());
        assertTrue(reservation.getIsReserved());
    }

    @Test
    public void testModifyReservation() {
        reservation.modifyReservation("2024-10-16", "20:00", 5);
        assertEquals("2024-10-16", reservation.getReservationDate());
        assertEquals("20:00", reservation.getReservationTime());
        assertEquals(5, reservation.getNumberOfGuests());
    }

    @Test
    public void testCancelReservation() {
        reservation.cancelReservation();
        assertFalse(reservation.getIsReserved());
    }

    @Test
    public void testGetters() {
        assertEquals("2024-10-15", reservation.getReservationDate());
        assertEquals("19:00", reservation.getReservationTime());
        assertEquals(4, reservation.getNumberOfGuests());
        assertEquals("Shirley", reservation.getCustomerName());
        assertTrue(reservation.getIsReserved());
    }

    @Test
    public void testSetters() {
        reservation.setReservationDate("2024-10-17");
        reservation.setReservationTime("21:00");
        reservation.setNumberOfGuests(6);
        reservation.setCustomerName("Marco");
        reservation.setIsReserved(true);

        assertEquals("2024-10-17", reservation.getReservationDate());
        assertEquals("21:00", reservation.getReservationTime());
        assertEquals(6, reservation.getNumberOfGuests());
        assertEquals("Marco", reservation.getCustomerName());
        assertTrue(reservation.getIsReserved());
    }
}
