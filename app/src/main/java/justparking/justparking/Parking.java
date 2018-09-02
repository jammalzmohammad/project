package justparking.justparking;

import java.util.ArrayList;

/**
 * Created by Mohammad Aljammal on 4/2/2018.
 */

public class Parking {


    private ArrayList<ParkingLocation> parkings = new ArrayList<>();


    public Parking()
    {
    }

    public Parking( ArrayList<ParkingLocation> parkings) {

        this.parkings = parkings;
    }

    public ArrayList<ParkingLocation> getParkings() {
        return parkings;
    }

    public void setParkings(ArrayList<ParkingLocation> parkings) {
        this.parkings = parkings;
    }

    public void addParking(ParkingLocation parkingLocation)
    {
        parkings.add(parkingLocation);
    }

    @Override
    public String toString() {
        return "Parking{" +
                ", parkings=" + parkings +
                '}';
    }

}
