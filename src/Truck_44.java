import java.util.Date;

public abstract class Truck_44 extends Vehicle_44 {
    private double loadingCapacity;

    public Truck_44(int plateNumber, int numberOfTires, boolean remotable, double loadingCapacity) {
        super(plateNumber, numberOfTires, remotable);
        this.loadingCapacity = loadingCapacity;
    }

    @Override
    public void bookMe(Date rentDayStart, Date rentDayEnd) throws SorryWeDontHaveThatOneException, BookingErrorsException {
        // At least 7 days prior to rental
        Date now = new Date();
        long diff = rentDayStart.getTime() - now.getTime();
        long days = diff / (1000L * 60 * 60 * 24);

        if (days < 7) {
            throw new BookingErrorsException("Trucks must be booked at least 7 days before rental.");
        }

        super.bookMe(rentDayStart, rentDayEnd);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + "Number of tires: " + this.getNumberOfTires() + "\n Plate Number: " +
          getPlateNumber() + "\n Daily fee: " + getDailyFee() + "\n ID: " + getId() +
          "\n Loading capacity: " + loadingCapacity + "\n Current Load: " + getCurrentLoad();
    }

    public double getLoadingCapacity() {return loadingCapacity;}
    public void setLoadingCapacity(double lc) {loadingCapacity = lc;}
}
