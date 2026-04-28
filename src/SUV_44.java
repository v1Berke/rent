public class SUV_44 extends Car_44 {
    private String wd;

    public SUV_44(int plateNumber, int numberOfTires, boolean remotable, String wd) {
        super(plateNumber, numberOfTires, remotable);
        if (!isValidWD(wd)) {
            throw new IllegalArgumentException("Invalid wheel drive type. Must be RWD, FWD, 4WD, or AWD.");
        }
        this.wd = wd;
    }

    private boolean isValidWD(String wd) {
        return wd.equals("RWD") || wd.equals("FWD") || wd.equals("4WD") || wd.equals("AWD");
    }

    public void setWheelDrive(String wd) {
        if (!isValidWD(wd)) {
            throw new IllegalArgumentException("Invalid wheel drive type.");
        }
        this.wd = wd;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + "Number of tires: " + this.getNumberOfTires() + "\n Plate Number: " +
        getPlateNumber() + "\n Daily fee: " + getDailyFee() + "\n ID: " +
         getId() + "\n Drive Type: " + getWd() ;
    }

    public String getWd() {return wd;}
}
