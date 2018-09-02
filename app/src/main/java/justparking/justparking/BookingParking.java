package justparking.justparking;

/**
 * Created by Mohammad Aljammal on 4/15/2018.
 */

public class BookingParking {

    String time;
    String loca;
    boolean status=true;
    String id;
    public static BookingParking bookingParking=new BookingParking();


    public BookingParking()
    {

    }
    public BookingParking(String time, String location, boolean status) {
        this.time = time;
        this.loca = location;
        this.status=status;
    }

    public BookingParking(String time, String loca, boolean status, String id) {
        this.time = time;
        this.loca = loca;
        this.status = status;
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLoca() {
        return loca;
    }

    public void setLoca(String location) {
        this.loca = location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
