import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Vehicle_44 implements Serializable, VehicleActions_44 {
    private static int count = loadCount();
    private int id;
    private final int plateNumber, numberOfTires;
    private double dailyFee;
    private Date rentDayStart;
    private Date rentDayEnd;
    private boolean isBooked;
    private boolean remotable;
    private boolean rented;
    private double currentLoad;

    private static final String COUNT_FILE = "vehicle_count.dat";
    
    private List<Reservation_44> reservations = new ArrayList<>();
    private List<Rental_44> rentals = new ArrayList<>();

    

    public static class SorryWeDontHaveThatOneException extends Exception {
        public SorryWeDontHaveThatOneException(String message) {
            super(message);
        }
    }

    public static class NoCancellationYouMustPayException extends Exception {
        public NoCancellationYouMustPayException(String message){
            super(message);
        }
    }

    public static class OverWeightException extends Exception {
        public OverWeightException(String message){
            super(message);
        }
    }
    public static class BookingErrorsException extends Exception {
        public BookingErrorsException(String message){
            super(message);
        }
    }

    public Vehicle_44(int plateNumber, int numberOfTires, boolean remotable){
        this.plateNumber = plateNumber;
        this.numberOfTires = numberOfTires;
        this.remotable = remotable;
        this.id = count++;
        this.isBooked = false;
        this.rented = false;
        this.currentLoad = 0;

        saveCount(count);
    }


    public void setDailyFee(double dailyFee) {
        this.dailyFee = dailyFee;
    }

    public double getTotalFee() {
        if (rentDayStart == null) return 0.0;
        Date today = new Date();
        long diff = today.getTime() - rentDayStart.getTime();
        int numberOfDays = (int) (diff / (1000L * 60 * 60 * 24));
        return numberOfDays * dailyFee;
    }

    public void bookMe(Date rentDayStart, Date rentDayEnd) throws SorryWeDontHaveThatOneException, BookingErrorsException {   
        for (Reservation_44 r : reservations) {
            if(r.conflictsWith(rentDayStart, rentDayEnd)) {
                throw new SorryWeDontHaveThatOneException("Vehicle is already booked in the given date range");
            }
        }
        Reservation_44 newRes = new Reservation_44(rentDayStart, rentDayEnd, this);
        reservations.add(newRes);
        System.out.println("Vehicle " + id + " booked from " + rentDayStart + " to " + rentDayEnd);
    }

    
    public boolean isBookedInRange(Date start, Date end) {
            for (Reservation_44 r : reservations) {
                if (r.conflictsWith(start, end)) {
                    return true;
                }
            }
            return false;
    } 

    public void cancelMe(Date rentDayStart, Date rentCancel) throws NoCancellationYouMustPayException {
        Reservation_44 toRemove = null;
        for (Reservation_44 r : reservations) {
            if (r.getStartDate().equals(rentDayStart) && r.getEndDate().equals(rentCancel)) {
                toRemove = r;
                break;
            }
        }
        if (toRemove != null) {
            Date now = new Date();
            if (now.before(toRemove.getStartDate())) {
                reservations.remove(toRemove);
                System.out.println("Reservation canceled.");
            } else {
                throw new NoCancellationYouMustPayException("Cannot cancel after rental started.");
            }
        } else {
            System.out.println("No matching reservation found.");
        }
    }

    public void rentMe(Date rentStart, Date rentEnd, String loc) throws SorryWeDontHaveThatOneException {
        for (Rental_44 r : rentals) {
            if (r.conflictsWith(rentStart, rentEnd)) {
                throw new SorryWeDontHaveThatOneException("Vehicle is already rented in this date range.");
            }
        }

        Rental_44 newRental = new Rental_44(rentStart, rentEnd, this);
        newRental.activate();
        rentals.add(newRental);
        this.rentDayStart = rentStart;
        this.rentDayEnd = rentEnd;
        this.rented = true;
        System.out.println(id + " numbered vehicle rented from " + rentStart + " to " + rentEnd);
    }

    public void dropMe() {
        Rental_44 rented = null;

        for (Rental_44 r : rentals) {
            if (r.isActive()) {
                rented = r;
                break;
            }
        }

        if (rented != null) {
            rented.deactivate();
            this.rented = false;
            System.out.println(id + " vehicle has been dropped off. Your total fee is " + getTotalFee());
        } else {
            System.out.println("No active rental found to drop.");
        }
    }

    public boolean isRentedInRange(Date start, Date end) {
        for (Rental_44 r : rentals) {
            if (r.conflictsWith(start, end)) {
                return true;
            }
        }
        return false;
    }

    protected double getLoadingCapacity() {
        return 0;
    }

    public void loadMe(double additionalLoad) throws OverWeightException {
        if (currentLoad + additionalLoad > getLoadingCapacity()) {
            throw new OverWeightException("Load exceeds the loading capacity of the vehicle!");
        }
        currentLoad += additionalLoad;
        System.out.println("Load successful. Current load: " + currentLoad);
    }

    private static void saveCount(int count) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(COUNT_FILE))) {
            dos.writeInt(count);
        } catch (IOException e) {
            System.out.println("Could not save user count.");
        }
    }

    private static int loadCount() {
        File file = new File(COUNT_FILE);
        if (!file.exists()) {
            return 1000;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(COUNT_FILE))) {
            return dis.readInt();
        } catch (IOException e) {
            System.out.println("Could not load user count. Defaulting to 1000.");
            return 1000;
        }
    }

    @Override
    public String toString() {
        return numberOfTires + plateNumber + dailyFee + id + "";
    }

    public List<Reservation_44> getReservations() {return reservations;}
    public List<Rental_44> getRentals() {return rentals;}
    public int getNumberOfTires() {return numberOfTires;}
    public int getId() {return id;}
    public int getPlateNumber() {return plateNumber;}
    public boolean isBooked() {return isBooked;}
    public boolean isRented() {return rented;}
    public boolean isRemotable() {return remotable;}
    public Date getRentDayStart() {return rentDayStart;}
    public Date getRentDayEnd() {return rentDayEnd;}
    public double getDailyFee() {return dailyFee;}
    public double getCurrentLoad() {return currentLoad;}
}
