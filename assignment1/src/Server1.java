import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Server1 {
    private static final int port =
            8080;
    private static ServerSocket server = null;
    private static
    Socket socket = null;

    public static void main(String[] args) {
//Create IO Objects
        BufferedReader in = null;
        PrintWriter out = null;
        Scanner consoleInput = new Scanner(System.in);
//Start Server
        try {
            System.out.println("Server is starting ...");
            server = new ServerSocket(port);
            System.out.println("Server Has Started");
        } catch (IOException e) {
            System.out.println("Can not listen to port: " + port);
            System.exit(-1);
        }
        while (true) {
            try {
                socket = server.accept();
                System.out.println("Client has been connected");
            } catch
            (IOException e) {
                System.out.println();
                System.exit(-1);
            }
            try {
                in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()
                        )
                );
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("NSU CSE338 LAB Game Server");

                while (socket.isConnected()) {
                    int clientInput = Integer.parseInt(in.readLine());
                    int serverRandomInput = new Random().nextInt(2) + 1;
                    System.out.println(serverRandomInput);
                    System.out.println(clientInput);
                    if (clientInput == serverRandomInput) {
                        out.println("Server Input =  " + serverRandomInput + " & Draw! ");
                    }
                    if (clientInput == 1 && serverRandomInput == 2) {
                        out.println("Server Input =  " + serverRandomInput + " & Server Wins! !");
                    } else if (clientInput == 1 && serverRandomInput == 3) {
                        out.println("Server Input =  " + serverRandomInput + " & Client Wins! !");
                    } else if (clientInput == 2 && serverRandomInput == 1) {
                        out.println("Server Input =  " + serverRandomInput + " & Client Wins! !");
                    } else if (clientInput == 2 && serverRandomInput == 3) {
                        out.println("Server Input =  " + serverRandomInput + " & Server Wins! !");
                    } else if (clientInput == 3 && serverRandomInput == 1) {
                        out.println("Server Input =  " + serverRandomInput + " & Server Wins! !");
                    } else if (clientInput == 3 && serverRandomInput == 2) {
                        out.println("Server Input =  " + serverRandomInput + " & Client Wins! !");
                    }else{
                        out.println("Wrong Input! ");
                    }
                }
            } catch (IOException e) {
                System.out.print("Client Left");
                consoleInput.close();
            }
        }
    }

}