package TCPsocketProgramming;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


class ClientHandler extends Thread {
    private final Socket clientSocket;
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    @Override
    public void run() {
        try (
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream())
        ) {
            while (true) {
                dos.writeUTF("Enter two integers and an operation (Sum, Subtract, Multiplication, Division, Modules) " +
                        "or type 'ENDS' to disconnect:");
                String input = dis.readUTF();

                if (input.equalsIgnoreCase("ENDS")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                String[] parts = input.split(",");
                if (parts.length != 3) {
                    dos.writeUTF("Invalid input format. Use: <int1>,<int2>,<operation>");
                    continue;
                }
                try {
                    int num1 = Integer.parseInt(parts[0].trim());
                    int num2 = Integer.parseInt(parts[1].trim());
                    String operation = parts[2].trim().toLowerCase();
                    String result = calculate(num1, num2, operation);
                    dos.writeUTF(result);
                } catch (NumberFormatException e) {
                    dos.writeUTF("Invalid numbers. Please try again.");
                }}
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }}}


    private String calculate(int num1, int num2, String operation) {
        switch (operation) {
            case "sum":
                return "Result: " + (num1 + num2);
            case "subtract":
                return "Result: " + (num1 - num2);
            case "multiplication":
                return "Result: " + (num1 * num2);
            case "division":
                if (num2 == 0) {
                    return "Error: Division by zero.";
                }
                return "Result: " + (num1 / num2);
            case "modules":
                return "Result: " + (num1 % num2);
            default:
                return "Invalid operation. Use: Sum, Subtract, Multiplication, Division, Modules.";
        }}}