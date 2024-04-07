import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class HotelReservationSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "rot";
    private static final String password = "abhi@2417";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            while(true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Reserve a Room");
                System.out.println("2. View reservation");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. Exit");
                System.out.println("Choose an option: ");
                int choice = sc.nextInt();
                switch(choice){
                    case 1:
                        reserveRoom(connection, sc);
                        break;
                    case 2:
                        viewReservations(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, sc);
                        break;
                    case 4:
                        updateReservation(connection, sc);
                        break;
                    case 5:
                        deleteReservation(connection, sc);
                        break;
                    case 0:
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try Again");
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    private static void reserveRoom(Connection connection, Scanner sc){
        try{
            System.out.println("Enter guest name: ");
            String guestName = sc.next();
            sc.nextLine();
            System.out.println("Enter room number: ");
            int roomNumber = sc.nextInt();
            System.out.println("Enter contact number: ");
            String contactNumber = sc.next();

            //Query to insert data in Db
            String sql = "INSERT INTO reservations (guest_name, room_number, contact_number)" +
                    "VALUES('"+ guestName + "', "+ roomNumber +", '" + contactNumber + "')";

            try(Statement statement = connection.createStatement()){
                int affectedRows = statement.executeUpdate(sql);

                if(affectedRows > 0){
                    System.out.println("Reservation successful");
                }else{
                    System.out.println("Reservation failed");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void viewReservations(Connection connection) throws SQLException{
        String sql = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservations";

        try(Statement statement = connection.createStatement());

    }
}
