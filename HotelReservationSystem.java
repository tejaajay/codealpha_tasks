package com.code_alpha.task1;

import java.util.*;

class Room {
	private int roomNumber;
	private String roomType;
	private boolean isAvailable;
	private double pricePerNight;

	public Room(int roomNumber, String roomType, double pricePerNight) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.isAvailable = true;
		this.pricePerNight = pricePerNight;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	@Override
	public String toString() {
		return "Room " + roomNumber + " (" + roomType + ") - $" + pricePerNight + " per night";
	}
}

class Reservation {
	private String customerName;
	private Room room;
	private int nights;
	private double totalPrice;

	public Reservation(String customerName, Room room, int nights) {
		this.customerName = customerName;
		this.room = room;
		this.nights = nights;
		this.totalPrice = room.getPricePerNight() * nights;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Room getRoom() {
		return room;
	}

	public int getNights() {
		return nights;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		return "Booking for " + customerName + ": Room " + room.getRoomNumber() + " (" + room.getRoomType() + ") for "
				+ nights + " night(s). Total: $" + totalPrice;
	}
}

public class HotelReservationSystem {
	private static List<Room> rooms = new ArrayList<>();
	private static List<Reservation> reservations = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		rooms.add(new Room(101, "Standard", 100));
		rooms.add(new Room(102, "Standard", 100));
		rooms.add(new Room(201, "Deluxe", 150));
		rooms.add(new Room(202, "Deluxe", 150));
		rooms.add(new Room(301, "Suite", 200));
		rooms.add(new Room(302, "Suite", 200));

		
		int option;
		do {
			displayMenu();
			option = getOption();
			switch (option) {
			case 1:
				searchAvailableRooms();
				break;
			case 2:
				makeReservation();
				break;
			case 3:
				viewBookingDetails();
				break;
			case 4:
				System.out.println("Exiting the system. Thank you for using the hotel reservation system!");
				break;
			default:
				System.out.println("Invalid option. Please choose again.");
				break;
			}
		} while (option != 4);

		scanner.close();
	}

	
	private static void displayMenu() {
		System.out.println("\nHotel Reservation System Menu:");
		System.out.println("1. Search Available Rooms");
		System.out.println("2. Make a Reservation");
		System.out.println("3. View Booking Details");
		System.out.println("4. Exit");
		System.out.print("Please choose an option (1-4): ");
	}

	
	private static int getOption() {
		int option = -1;
		try {
			option = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid number.");
		}
		return option;
	}

	
	private static void searchAvailableRooms() {
		System.out.println("\nAvailable Rooms:");
		for (Room room : rooms) {
			if (room.isAvailable()) {
				System.out.println(room);
			}
		}
	}

	
	private static void makeReservation() {
		System.out.print("Enter customer name: ");
		String customerName = scanner.nextLine();

		System.out.print("Enter room number to reserve: ");
		int roomNumber;
		try {
			roomNumber = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid room number. Please try again.");
			return;
		}

		Room selectedRoom = null;
		for (Room room : rooms) {
			if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
				selectedRoom = room;
				break;
			}
		}

		if (selectedRoom == null) {
			System.out.println("Room is not available or doesn't exist.");
			return;
		}

		System.out.print("Enter number of nights: ");
		int nights;
		try {
			nights = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid number of nights. Please try again.");
			return;
		}

		Reservation reservation = new Reservation(customerName, selectedRoom, nights);
		reservations.add(reservation);
		selectedRoom.setAvailable(false);

		System.out.printf("Reservation successful! Total amount to be paid: $%.2f\n", reservation.getTotalPrice());
		System.out.print("Enter payment amount: ");
		try {
			double payment = Double.parseDouble(scanner.nextLine());
			if (payment >= reservation.getTotalPrice()) {
				System.out.println("Payment successful!");
			} else {
				System.out.println("Insufficient payment. Please pay the full amount.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid payment. Reservation complete but payment failed.");
		}
	}

	private static void viewBookingDetails() {
		System.out.print("Enter customer name to view reservation: ");
		String customerName = scanner.nextLine();
		boolean found = false;

		for (Reservation reservation : reservations) {
			if (reservation.getCustomerName().equalsIgnoreCase(customerName)) {
				System.out.println(reservation);
				found = true;
			}
		}

		if (!found) {
			System.out.println("No reservation found for customer: " + customerName);
		}
	}
}
