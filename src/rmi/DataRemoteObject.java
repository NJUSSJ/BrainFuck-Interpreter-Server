package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.ExecuteService;
import service.IOService;
import service.UserService;
import serveImpl.*;

public class DataRemoteObject extends UnicastRemoteObject implements IOService ,ExecuteService,UserService{

	private static final long serialVersionUID = 4029039744279087114L;

	private IOService iOService;
	private ExecuteService executeService;
	private UserService userService;

	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		executeService=new ExecuteImpl();
		userService=new UserImpl();
		// TODO 自动生成的构造函数存根
	}

	@Override
	public boolean writeFile(String file, String userId, String fileName) throws RemoteException {
		// TODO 自动生成的方法存根
		return iOService.writeFile(file, userId, fileName);
	}

	@Override
	public String readFile(String userId, String fileName) throws RemoteException {
		// TODO 自动生成的方法存根
		return iOService.readFile(userId, fileName);
	}

	@Override
	public String readFileList(String userId) throws RemoteException {
		// TODO 自动生成的方法存根
		return iOService.readFileList(userId);
	}

	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO 自动生成的方法存根
		return executeService.execute(code, param);
	}

	@Override
	public Boolean Log_in(String username, String password) throws RemoteException {
		// TODO 自动生成的方法存根
		return userService.Log_in(username, password);
	}

	@Override
	public void Log_out() throws RemoteException {
		userService.Log_out();

	}

	@Override
	public Boolean new_Account(String username, String password) throws RemoteException {
		// TODO 自动生成的方法存根
		return userService.new_Account(username, password);

	}

	@Override
	public boolean writeFilelist(String userId,String filename,boolean if_newfile,String code) throws RemoteException {
		// TODO Auto-generated method stub
		return iOService.writeFilelist(userId, filename,if_newfile,code);

	}



}
