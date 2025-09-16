import java.rmi.Naming;

public class CalcClient {
    public static void main(String[] args) {
        try {
            ICalc calc = (ICalc) Naming.lookup("rmi://localhost:5000/calc");
            System.out.println("Addition: " + calc.add(5, 3));
            System.out.println("Subtraction: " + calc.sub(5, 3));
            System.out.println("Multiplication: " + calc.mul(5, 3));
            System.out.println("Division: " + calc.div(5, 3));
        } catch (Exception e) {
            System.out.println("Client Exception: " + e);
        }
    }
}
