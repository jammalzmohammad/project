package justparking.justparking;

/**
 * Created by Mohammad Aljammal on 4/4/2018.
 */

public class ParkingLocation
{
        private double lat;
        private double lon;
        private boolean status;
        private String id;
        private  String rand;

        public ParkingLocation() {
        }

        public ParkingLocation(double lat, double lon, boolean status, String id) {
            this.lat = lat;
            this.lon = lon;
            this.status = status;
            this.id = id;
        }

    public ParkingLocation(double lat, double lon, boolean status, String id, String rand) {
        this.lat = lat;
        this.lon = lon;
        this.status = status;
        this.id = id;
        this.rand = rand;
    }

    public String getRand() {
        return rand;
    }

    public void setRand(String rand) {
        this.rand = rand;
    }

    public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
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

    @Override
    public String toString() {
        return "ParkingLocation{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", status=" + status +
                ", id='" + id + '\'' +
                '}';
    }
}
