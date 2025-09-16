import java.rmi.Naming;

public class CalcServer {
    public static void main(String[] args) {
        try {
            CalcImpl calc = new CalcImpl();
            Naming.rebind("rmi://localhost:5000/calc", calc);
            System.out.println("RMI Server is running at rmi://localhost:5000/calc");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e);
        }
    }
}
