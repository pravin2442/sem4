import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalc extends Remote {
    int add(int a, int b) throws RemoteException;
    int sub(int a, int b) throws RemoteException;
    int mul(int a, int b) throws RemoteException;
    int div(int a, int b) throws RemoteException;
}



// RMI = Remote Method Invocation (Java’s way to do distributed object communication).
// Needs 4 parts: Interface, Implementation, Server, Client.
// Uses rmiregistry for binding and lookup.
// Methods throw RemoteException.
// Objects extend UnicastRemoteObject to allow remote access.