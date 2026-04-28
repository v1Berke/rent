import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class Test_44 {
    private static Scanner scan = new Scanner(System.in);
    private static final VehiclePark_44 vehiclePark = new VehiclePark_44();

    private static class InputEntryException extends Exception {
        public InputEntryException(String msg){
            super(msg);
        } 
    }

    public static void main(String[] args) throws Exception {

        List<Vehicle_44> loadedVehicles = loadVehicles();
        vehiclePark.setVehicles(loadedVehicles);

        ArrayList<User_44> users = (ArrayList<User_44>) UserDatabase_44.loadUsers();
        
        boolean adminExists = false;

        for (User_44 user : users) {
            if (user.isAdmin()) {
                adminExists = true;
                break;
            }
        }

        if (!adminExists) {
            User_44 admin = new User_44("admin", "admin", "admin");
            admin.setAdmin();
            users.add(admin);
            UserDatabase_44.saveUsers(users); 
        }


        while (true) {
            System.out.println("\nWelcome to Rental Application.");
            System.out.println("1- Log in");
            System.out.println("2- Create new account");
            System.out.println("3- Exit");
            System.out.print("Enter: ");

            int input;
            try {
                input = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue;
            }

            switch (input) {
                case 1: // Log in 
                    User_44 user = login();
                    if(user != null) {
                        if (user.isAdmin()) adminMenu(user);
                        else customerMenu(user);
                    }
                    break;

                case 2: // Registration
                    register();
                    break;

                case 3: // EXIT
                    System.out.println("Exiting...");
                    scan.close();
                    return;

                default:
                    System.out.println("Please choose between 1 and 3.");
            }
        }
    }
    
    private static void register() {
        try {
            System.out.print("Name: ");
            String name = scan.nextLine().trim();

            if (name.isEmpty()) {
                throw new InputEntryException("Name cannot be empty.");
            }

            System.out.print("Surname: ");
            String surname = scan.nextLine().trim();

            if (surname.isEmpty()) {
                throw new InputEntryException("Surname cannot be empty.");
            }

            System.out.print("Password: ");
            String password = scan.nextLine();

            if (password.length() < 4) {
                throw new InputEntryException("Password must be at least 4 characters.");
            }

            List<User_44> users = UserDatabase_44.loadUsers();
            User_44 newUser = new User_44(name, surname, password);
            users.add(newUser);
            UserDatabase_44.saveUsers(users);

            System.out.println("Your registration has been completed successfully. Your ID is " + newUser.getUserId() + ". Do not forget the ID given to you for your login process.");
    
        } catch (InputEntryException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
        }
    }

    private static User_44 login() {
        try {
            System.out.print("Enter Your ID: ");
            int ID = scan.nextInt();
            scan.nextLine();

            System.out.print("Enter Your Password: ");
            String password = scan.nextLine();

            List<User_44> users = UserDatabase_44.loadUsers();

            for (User_44 u : users) {
                if(u.getUserId() == ID && u.getPassword().equals(password)) {
                    System.out.println("Log in successful.");
                    return u;
                }
            }

            throw new InputEntryException("Incorrect ID or password.");
        } catch (InputEntryException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
        }
        return null;
    }
    
    public static void adminMenu(User_44 user) {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Display all vehicles");
            System.out.println("2. Display available vehicles (by date)");
            System.out.println("3. Add a new vehicle");
            System.out.println("4. Remove vehicle");
            System.out.println("5. Daily Report");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    vehiclePark.displayVehicles();
                    break;

                case "2":
                    try {
                        System.out.print("Enter start date (dd/MM/yyyy): ");
                        Date start = new SimpleDateFormat("dd/MM/yyyy").parse(scan.nextLine());
                        System.out.print("Enter end date (dd/MM/yyyy): ");
                        Date end = new SimpleDateFormat("dd/MM/yyyy").parse(scan.nextLine());
                        vehiclePark.displayAvailableVehicles(start, end);
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Enter plate number: ");
                        int plate = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Enter number of tires: ");
                        int tires = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Is it remotable? (true/false): ");
                        boolean remotable = scan.nextBoolean();
                        System.out.print("Enter daily fee: ");
                        double fee = scan.nextDouble();
                        scan.nextLine();
                        System.out.print("Enter type: ");
                        String type = scan.nextLine().toLowerCase().trim();

                        Vehicle_44 vehicle;
                        if (type.equals("sport")) {
                            System.out.print("Enter horse power: ");
                            int hp = scan.nextInt();
                            scan.nextLine();
                            vehicle = new Sport_44(plate, tires, remotable, hp);
                        } else if (type.equals("station wagon")) {
                            System.out.print("Enter Loading Capacity: ");
                            double loadingCapacity = scan.nextDouble();
                            scan.nextLine();
                            vehicle = new StationWagon_44(plate, tires, remotable, loadingCapacity);
                        } else if (type.equals("small truck")) {
                            System.out.print("Enter Loading Capacity: ");
                            double loadingCapacity = scan.nextDouble();
                            scan.nextLine();
                            vehicle = new SmallTruck_44(plate, tires, remotable, loadingCapacity);
                        } else if (type.equals("transport truck")) {
                            System.out.print("Enter loading capacity: ");
                            double loadingCapacity = scan.nextDouble();
                            scan.nextLine();
                            System.out.print("Is the car goes abroad (yes/no): ");
                            String abroad = scan.nextLine();
                            boolean bool = abroad.equals("yes");
                            vehicle = new TransportTruck_44(plate, tires, remotable, loadingCapacity, bool);
                        } else if (type.equals("suv")) {
                            System.out.print("Enter wheel drive type: ");
                            String wd = scan.nextLine().toUpperCase();
                            if(remotable) remotable = false;
                            System.out.println("SUV's neither remote deliverable, nor remote droppable off.");
                            vehicle = new SUV_44(plate, tires, remotable, wd);
                        } else {
                            System.out.println("Invalid type.");
                            break;
                        }

                        vehicle.setDailyFee(fee);
                        vehiclePark.addVehicle(vehicle);
                        System.out.println("Vehicle added successfully.");
                    } catch (Exception e) {
                        System.out.println("Invalid input. Vehicle not added.");
                    }
                    break;

                case "4":
                    System.out.print("Enter vehicle ID to remove: ");
                    try {
                        int id = scan.nextInt();
                        scan.nextLine();
                        vehiclePark.removeVehicle(id);
                        System.out.println("Vehicle removed if ID was found.");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ID.");
                    } 
                    break;

                case "5":
                    System.out.print("Enter file name for report (e.g. report.txt): ");
                    String fileName = scan.nextLine();
                    vehiclePark.dailyReport(fileName);
                    System.out.println("Report generated.");
                    break;

                case "6":
                    saveVehiclesToFile("vehicles.dat");
                    System.out.println("Exiting and saving data...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static List<Vehicle_44> loadVehicles() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vehicles.dat"))) {
            VehiclePark_44 loadedPark = (VehiclePark_44) ois.readObject();
            return loadedPark.getVehicles();
        } catch (FileNotFoundException e) {
            System.out.println("vehicles.dat not found, starting with empty vehicle list.");
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error loading vehicles.dat: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    private static void saveVehiclesToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(vehiclePark);
            System.out.println("Vehicle park saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving vehicle data.");
        }
    }

    private static void customerMenu(User_44 user) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false); 

        while (true) {
            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1. Display all vehicles");
            System.out.println("2. Display available vehicles (by date)");
            System.out.println("3. Display available vehicles by type (by date)");
            System.out.println("4. Book a vehicle");
            System.out.println("5. Cancel my booking");
            System.out.println("6. Rent a vehicle");
            System.out.println("7. Drop a vehicle");
            System.out.println("8. Load a vehicle");
            System.out.println("9. Quit");
            System.out.print("Choose an option: ");

            String choice = scan.nextLine();

            try {
                switch (choice) {
                    case "1":
                        vehiclePark.displayVehicles();
                        break;

                    case "2":
                        System.out.print("Enter start date (dd/MM/yyyy): ");
                        Date start = formatter.parse(scan.nextLine());
                        System.out.print("Enter end date (dd/MM/yyyy): ");
                        Date end = formatter.parse(scan.nextLine());
                        vehiclePark.displayAvailableVehicles(start, end);
                        break;

                    case "3":
                        System.out.print("Enter start date (dd/MM/yyyy): ");
                        Date sDate = formatter.parse(scan.nextLine());
                        System.out.print("Enter end date (dd/MM/yyyy): ");
                        Date eDate = formatter.parse(scan.nextLine());
                        System.out.print("Enter vehicle type (e.g. SUV, Sports, SmallTruck): ");
                        String type = scan.nextLine();
                        vehiclePark.displayAvailableVehicles(sDate, eDate, type);
                        break;

                    case "4":
                        System.out.print("Enter vehicle ID to book: ");
                        int bookId = scan.nextInt();
                        scan.nextLine();
                        Vehicle_44 bookVehicle = findVehicleById(bookId);
                        if (bookVehicle == null) {
                            System.out.println("Vehicle not found.");
                            break;
                        }
                        System.out.print("Enter booking start date (dd/MM/yyyy): ");
                        Date bStart = formatter.parse(scan.nextLine());
                        System.out.print("Enter booking end date (dd/MM/yyyy): ");
                        Date bEnd = formatter.parse(scan.nextLine());
                        vehiclePark.bookVehicle(bookVehicle, bStart, bEnd);
                        break;

                    case "5":
                        System.out.print("Enter vehicle ID to cancel booking: ");
                        int cancelId = scan.nextInt();
                        scan.nextLine();
                        Vehicle_44 cancelVehicle = findVehicleById(cancelId);
                        if (cancelVehicle == null) {
                            System.out.println("Vehicle not found.");
                            break;
                        }
                        System.out.print("Enter original booking start date (dd/MM/yyyy): ");
                        Date originalStart = formatter.parse(scan.nextLine());
                        System.out.print("Enter cancellation date (dd/MM/yyyy): ");
                        Date cancelDate = formatter.parse(scan.nextLine());
                        vehiclePark.cancelBooking(cancelVehicle, originalStart, cancelDate);
                        break;

                    case "6":
                        System.out.print("Enter vehicle ID to rent: ");
                        int rentId = scan.nextInt();
                        scan.nextLine();
                        Vehicle_44 rentVehicle = findVehicleById(rentId);
                        if (rentVehicle == null) {
                            System.out.println("Vehicle not found.");
                            break;
                        }
                        System.out.print("Enter rent start date (dd/MM/yyyy): ");
                        Date rentStart = formatter.parse(scan.nextLine());
                        System.out.print("Enter rent end date (dd/MM/yyyy): ");
                        Date rentEnd = formatter.parse(scan.nextLine());
                        System.out.print("Enter delivery location: ");
                        String location = scan.nextLine();
                        vehiclePark.rentVehicle(rentVehicle, rentStart, rentEnd, location);
                        break;

                    case "7":
                        System.out.print("Enter vehicle ID to drop: ");
                        int dropId = scan.nextInt();
                        scan.nextLine();
                        Vehicle_44 dropVehicle = findVehicleById(dropId);
                        if (dropVehicle == null) {
                            System.out.println("Vehicle not found.");
                            break;
                        }
                        vehiclePark.dropVehicle(dropVehicle);
                        break;

                    case "8":
                        System.out.print("Enter vehicle ID to load: ");
                        int loadId = scan.nextInt();
                        scan.nextLine();
                        Vehicle_44 loadVehicle = findVehicleById(loadId);
                        if (loadVehicle == null) {
                            System.out.println("Vehicle not found.");
                            break;
                        }
                        System.out.print("Enter load amount: ");
                        double load = scan.nextDouble();
                        scan.nextLine();
                        vehiclePark.loadVehicle(loadVehicle, load);
                        break;

                    case "9":
                        saveVehiclesToFile("vehicles.dat");
                        System.out.println("Exiting and saving data...");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (ParseException pe) {
                System.out.println("Invalid date format! Please enter date as dd/MM/yyyy.");
            } catch (NumberFormatException ne) {
                System.out.println("Invalid number input! Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static Vehicle_44 findVehicleById(int id) {
        for (Vehicle_44 v : vehiclePark.getVehicles()) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

}
