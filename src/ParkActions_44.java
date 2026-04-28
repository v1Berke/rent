import java.util.Date;

public interface ParkActions_44 {
    public void displayVehicles();
    public void displayAvailableVehicles(Date start, Date end);
    public void bookVehicle(Vehicle_44 vehicle, Date start, Date end);
    public void cancelBooking(Vehicle_44 vehicle, Date startDate, Date cancelDate);
    public boolean removeVehicle(int id);
    public void rentVehicle(Vehicle_44 vehicle,Date rentStart, Date rentEnd, String location);
    public void dropVehicle(Vehicle_44 vehicle);
    public void loadVehicle(Vehicle_44 vehicle, double load);
    public void addVehicle(Vehicle_44 v);
    public void dailyReport(String fileName);
}
