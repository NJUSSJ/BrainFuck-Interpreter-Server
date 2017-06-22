package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RemoteHelper {

	public RemoteHelper() {
		initServer();
	}
	
	public void initServer(){
		DataRemoteObject dataRemoteObject;
		
		try {
			dataRemoteObject=new DataRemoteObject();
			LocateRegistry.createRegistry(8889);
			Naming.bind("rmi://localhost:8889/IDE", dataRemoteObject);
			
		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (AlreadyBoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (RemoteException e) {
			// TODO: handle exception
			System.out.println("Registration Failed-->"+"Port in use");
		}
		
	}
}
