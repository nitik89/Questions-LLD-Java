package roomManagement;

import java.time.LocalDate;

public class Reservation {
    private final String id;
    private final Room room;
    private Guest guest;  // Assuming Guest is a custom class
    private ReservationStatus status;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    public Reservation(String id,Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.id = id;
        this.room = room;
        this.guest=guest;
        this.status = ReservationStatus.CONFIRMED;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    
    public synchronized void cancel(){
        if(status == ReservationStatus.CONFIRMED){
            status = ReservationStatus.CANCELLED;
            room.checkOut();
        }
        else {
            throw new IllegalStateException("Reservation is not confirmed");
        }
    }
    public String getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }





}
