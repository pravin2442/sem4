import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcImpl extends UnicastRemoteObject implements ICalc {

// trinjikoooo UnicastRemoteObject ==== allows remote access by exporting the object to the RMI runtime

    public CalcImpl() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public int sub(int a, int b) throws RemoteException {
        return a - b;
    }

    public int mul(int a, int b) throws RemoteException {
        return a * b;
    }

    public int div(int a, int b) throws RemoteException {
        if (b == 0) throw new ArithmeticException("Division by zero not allowed");
        return a / b;
    }
}
