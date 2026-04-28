import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehiclePark_44 implements ParkActions_44, Serializable{
    private ArrayList<Vehicle_44> vehicles;

    public VehiclePark_44() {
        vehicles = new ArrayList<>();
    }

    public void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the park.");
            return;
        }
        for (Vehicle_44 v : vehicles) {
            System.out.println(v);
        }
    }

    public void displayAvailableVehicles(Date start, Date end) {
        boolean found = false;
        for (Vehicle_44 v : vehicles) {
            if (!v.isBookedInRange(start, end) && !v.isRentedInRange(start, end)) {
                System.out.println(v.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No available vehicles in the given date range.");
    }

    public void displayAvailableVehicles(Date start, Date end, String vehicleTypeName) {
        boolean found = false;
        vehicleTypeName = vehicleTypeName + "_44";
        for (Vehicle_44 v : vehicles) {
            String className = v.getClass().getSimpleName();
            if (className.equalsIgnoreCase(vehicleTypeName) && !v.isBookedInRange(start, end) && !v.isRentedInRange(start, end)) {
                System.out.println(v.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No available vehicles of type " + vehicleTypeName + " in the given date range.");
    }

    // FOR CUSTOMER

    public void bookVehicle(Vehicle_44 vehicle, Date start, Date end) {
        try {
            vehicle.bookMe(start, end);
        } catch (Exception e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    public void cancelBooking(Vehicle_44 vehicle, Date startDate, Date cancelDate) {
        try {
            vehicle.cancelMe(startDate, cancelDate);
        } catch (Exception e) {
            System.out.println("Cancel failed: " + e.getMessage());
        }
    }

    public void rentVehicle(Vehicle_44 vehicle, Date rentStart, Date rentEnd, String location) {
        try {
            vehicle.rentMe(rentStart, rentEnd, location);
        } catch (Exception e) {
            System.out.println("Rent failed: " + e.getMessage());
        }
    }

    public void dropVehicle(Vehicle_44 vehicle) {
        vehicle.dropMe();
    }

    public void loadVehicle(Vehicle_44 vehicle, double load) {
        try {
            vehicle.loadMe(load);
        } catch (Exception e) {
            System.out.println("Load failed: " + e.getMessage());
        }
    }

    // FOR ADMIN

    public void addVehicle(Vehicle_44 v) {vehicles.add(v);}

    public boolean removeVehicle(int id) {
        for (Vehicle_44 v : vehicles) {
            if (v.getId() == id) {
                vehicles.remove(v);
                System.out.println("Vehicle removed: " + v);
                return true;    
            }
        }
        System.out.println("Vehicle with ID " + id + " not found.");
        return false;
    }

    public void dailyReport(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=== Daily Report ===\n\n");

            for (Vehicle_44 v : vehicles) {
                writer.write("Vehicle: " + v + "\n");

                // Reservations
                writer.write("  Booked from: ");
                for (Reservation_44 r : v.getReservations()) {
                    writer.write("[" + r.getStartDate() + " to " + r.getEndDate() + "] ");
                }

                // Rentals
                writer.write("\n  Rented: ");
                for (Rental_44 r : v.getRentals()) {
                    writer.write("[" + r.getStartDate() + " to " + r.getEndDate() + (r.isActive() ? " ACTIVE" : "") + "] ");
                }

                writer.write("\n\n");
            }

            System.out.println("Daily report written to " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to write report: " + e.getMessage());
        }
    }

    public void setVehicles(List<Vehicle_44> vehicles) {this.vehicles = new ArrayList<>(vehicles);}
    public List<Vehicle_44> getVehicles() {return vehicles;}
}
