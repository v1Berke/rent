public class StationWagon_44 extends Car_44{
    private double loadingCapacity;

    public StationWagon_44(int plateNumber, int numberOfTires, boolean remotable, double loadingCapacity) {
        super(plateNumber, numberOfTires, remotable);
        this.loadingCapacity = loadingCapacity;
    }

  
    public double getLoadingCapacity() {
        return loadingCapacity;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + "Number of tires: " + this.getNumberOfTires() + "\n Plate Number: " +
          getPlateNumber() + "\n Daily fee: " + getDailyFee() + "\n ID: " + getId() + "\n Loading capacity: " +
          getLoadingCapacity() + "\n Current Load: " + getCurrentLoad();
    }
}
