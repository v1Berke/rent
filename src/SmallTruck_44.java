public class SmallTruck_44 extends Truck_44{

    public SmallTruck_44(int plateNumber, int numberOfTires, boolean remotable, double loadingCapacity) {
        super(plateNumber, numberOfTires, remotable, loadingCapacity);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + "Number of tires: " + this.getNumberOfTires() + "\n Plate Number: "
         +  getPlateNumber() + "\n Daily fee: " + getDailyFee() +
          "\n ID: " + getId() + "\n Loading capacity: " + getLoadingCapacity() + "\n Current Load: " + getCurrentLoad();
    }
}