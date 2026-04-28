public abstract class Car_44 extends Vehicle_44{
    
    String color;
    int seatingCapacity;
    int numOfDoors;

    public Car_44(int plateNumber, int numberOfTires, boolean remotable) {
        super(plateNumber, numberOfTires, remotable);
    }
    
    
    public String getColor() {
        return color;
    }
    public int getseatingCapacity() {
        return seatingCapacity;
    }
    public int getnumOfDoors() {
        return numOfDoors;
    }
}
