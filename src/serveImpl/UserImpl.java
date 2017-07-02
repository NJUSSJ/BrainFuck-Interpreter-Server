package serveImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

import User.User;
import service.UserService;

public class UserImpl implements UserService {

	public static User user;
	public File file=new File("user_info.txt");


	@Override
	public Boolean Log_in(String username, String password) throws RemoteException {
		user=new User("");
		try {
			BufferedReader buf_r=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String temp=null;
			while((temp=buf_r.readLine())!=null){
				String strs[]=temp.split(" ");
				if(strs[0].equals(username)){
					if(strs[1].equals(password)){
						buf_r.close();
						user.setUsername(username);
						user.setPassword(password);
						return true;
					}
				}
			}
			buf_r.close();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void Log_out() throws RemoteException {
		user=null;
	}


	@Override
	public Boolean new_Account(String username, String password) throws RemoteException {
		user=new User("");
		if(username==null||username.equals("")){
			return false;
		}
		//Confirm state
		try {
			BufferedReader buf_r=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String temp=null;
			while((temp=buf_r.readLine())!=null){
				String strs[]=temp.split(" ");
				if(strs[0].equals(username)){
					buf_r.close();
					user=null;
					return false;
				}
			}
			buf_r.close();

			FileWriter writer=new FileWriter(file,true);
			writer.write(username+" "+password+"\n");
			writer.close();

			user.setUsername(username);
			user.setPassword(password);
		
			//建立用户数据
			String separator=File.separator;
		
			String directory="src"+separator+user.getUsername();

			String filename="filelist.txt";
			File file=new File(directory, filename);

			if(file.exists()){
				System.out.println("Existed");
			}
			else{
				file.getParentFile().mkdirs();
				file.createNewFile();
			
			}
			
			return true;
	
		} catch (IOException e) {
			// TODO: handle exception
		}
		return null;
	}



}
