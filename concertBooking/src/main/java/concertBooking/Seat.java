package concertBooking;

public class Seat {
    private final String id;
    private final String seatNumber;
    private final SeatType seatType;
    private final double price;
    private SeatStatus seatStatus;

    public Seat(String id, String seatNumber, SeatType seatType, double price) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public synchronized void book(){
        if(seatStatus == SeatStatus.AVAILABLE){
            seatStatus = SeatStatus.BOOKED;
        }
        else{
            throw new SeatNotAvailableException("Seat is already booked or reserved.");
        }
    }
    public synchronized void release(){
        if(seatStatus == SeatStatus.BOOKED){
            seatStatus = SeatStatus.AVAILABLE;
        }
        else{
            throw new SeatNotAvailableException("Seat is not booked or reserved.");
        }
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public SeatStatus getStatus() {
        return seatStatus;
    }
}
