> **Academic Project** — This project was developed as a homework assignment for an Object-Oriented Programming (OOP) course. It demonstrates core OOP concepts including inheritance, abstract classes, interfaces, and encapsulation.

# Vehicle Rental System

A console-based vehicle rental management system written in Java.

## Features

- **User system** — registration, login, admin/customer roles
- **Vehicle types** — Sport, Station Wagon, SUV, Small Truck, Transport Truck
- **Operations** — book, cancel booking, rent, drop off, load cargo
- **Admin panel** — add/remove vehicles, generate daily reports
- **Persistence** — vehicle park and user data saved between sessions via Java serialization

## Project Structure

```
src/
├── Test_44.java           # Entry point, console UI
├── Vehicle_44.java        # Abstract base class for all vehicles
├── Car_44.java            # Abstract car subclass
│   ├── Sport_44.java
│   ├── StationWagon_44.java
│   └── SUV_44.java
├── Truck_44.java          # Abstract truck subclass
│   ├── SmallTruck_44.java
│   └── TransportTruck_44.java
├── VehiclePark_44.java    # Vehicle fleet management
├── User_44.java           # User entity
├── UserDatabase_44.java   # User persistence
├── Reservation_44.java    # Reservation model
├── Rental_44.java         # Rental model
├── VehicleActions_44.java # Vehicle operations interface
└── ParkActions_44.java    # Park operations interface
```

## Running

Compile and run from the `src/` directory:

```bash
javac *.java
java Test_44
```

Default admin credentials: ID is printed on first run — password is `admin`.
