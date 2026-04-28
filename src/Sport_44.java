public class Sport_44 extends Car_44{
    private int HP;

    public Sport_44(int plateNumber, int numberOfTires, boolean remotable, int HP) {
        super(plateNumber, numberOfTires, remotable);
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + "Number of tires: " + this.getNumberOfTires() + "\n Plate Number: " +
        getPlateNumber() + "\n Daily fee: " + getDailyFee() + "\n ID: " +
         getId() + "\n Horse Power: " + getHP() ;
    }
}
