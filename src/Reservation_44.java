import java.io.Serializable;
import java.util.Date;

public class Reservation_44 implements Serializable {
        private Date startDate;
        private Date endDate;
        private Vehicle_44 vehicle;

        public Reservation_44(Date startDate, Date endDate, Vehicle_44 vehicle) {
            if (endDate.before(startDate)) {
                throw new IllegalArgumentException("End date cannot be before start date");
            }
            this.startDate = startDate;
            this.endDate = endDate;
            this.vehicle = vehicle;
        }

        public Vehicle_44 getVehicle() {return vehicle;}
        public Date getStartDate() {return startDate;}
        public Date getEndDate() {return endDate;}

        public boolean conflictsWith(Date rentDayStart, Date rentDayEnd) {
            return !(rentDayEnd.before(startDate) || rentDayStart.after(endDate));
        }
}

