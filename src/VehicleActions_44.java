import java.util.Date;

public interface VehicleActions_44 {
    void setDailyFee(double dailyFee);
    double getTotalFee();
    void bookMe(Date rentDayStart, Date rentDayEnd) throws Vehicle_44.SorryWeDontHaveThatOneException, Vehicle_44.BookingErrorsException;
    boolean isBookedInRange(Date start, Date end);
    void cancelMe(Date rentDayStart, Date rentCancel) throws Vehicle_44.NoCancellationYouMustPayException;
    void rentMe(Date rentStart, Date rentEnd, String loc) throws Vehicle_44.SorryWeDontHaveThatOneException;
    void dropMe();
    boolean isRentedInRange(Date start, Date end);
    void loadMe(double additionalLoad) throws Vehicle_44.OverWeightException;
}
