public class TransportTruck_44 extends Truck_44 {
    private boolean goesAbroad;

    public TransportTruck_44(int plateNumber, int numberOfTires, boolean remotable, double loadingCapacity, boolean goesAbroad) {
        super(plateNumber, numberOfTires, remotable, loadingCapacity);
        this.goesAbroad = goesAbroad;
    }

    public boolean isGoesAbroad() {
        return goesAbroad;
    }

    public void setGoesAbroad(boolean goesAbroad) {
        this.goesAbroad = goesAbroad;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + "Number of tires: " + this.getNumberOfTires() + "\n Plate Number: " +
          getPlateNumber() + "\n Daily fee: " + getDailyFee() + "\n ID: " + getId() + "\n Loading capacity: " +
          getLoadingCapacity() + "\n Current Load: " + getCurrentLoad() + "\n Is goes abroad: " + isGoesAbroad();
    }
}
