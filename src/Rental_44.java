import java.io.Serializable;
import java.util.Date;

public class Rental_44 implements Serializable {
    private Date startDate;
    private Date endDate;
    private Vehicle_44 vehicle;
    private boolean active;

    public Rental_44(Date startDate, Date endDate, Vehicle_44 vehicle) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicle = vehicle;
        this.active = false;
    }

    public boolean conflictsWith(Date start, Date end) {
        return !(end.before(startDate) || start.after(endDate));
    }

    public boolean isActive() {return active;}
    public void activate() {this.active = true;}
    public void deactivate() {this.active = false;}
    public Date getStartDate() {return startDate;}
    public Date getEndDate() {return endDate;}
    public Vehicle_44 getVehicle() {return vehicle;}
}
