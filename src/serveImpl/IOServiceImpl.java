package serveImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

import service.IOService;

public class IOServiceImpl implements IOService {

	@Override
	public boolean writeFile(String file, String userId, String fileName) throws RemoteException {
		
		return false;
	}

	@Override
	public String readFile(String userId, String fileName) throws RemoteException {
		// TODO 自动生成的方法存根
		String code="";
		try {
			String file_separater=java.io.File.separator;
			String file_path="src"+file_separater+userId+file_separater+fileName;
		
			java.io.File file=new java.io.File(file_path+".txt");
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String temp;
			while((temp=reader.readLine())!=null){
				code+=temp+"\n";
			}
			reader.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public String readFileList(String userId) throws RemoteException {
		String file_separater=java.io.File.separator;
		String file_path="src"+file_separater+userId+file_separater;
		java.io.File file_list=new java.io.File(file_path+"filelist.txt");
		String list="";
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file_list)));
			String temp;
			while((temp=reader.readLine())!=null){
				list+=temp+" ";
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean writeFilelist(String userId,String filename,boolean if_newfile,String code) throws RemoteException {

		String file_separater=java.io.File.separator;
		String separater=System.lineSeparator();
		String file_path="src"+file_separater+userId+file_separater;
		java.io.File file_list=new java.io.File(file_path+"filelist.txt");
		try {
			//judge if existed
			BufferedReader buf_r=new BufferedReader(new InputStreamReader(new FileInputStream(file_list)));
			String temp=null;
			while((temp=buf_r.readLine())!=null){
				if(temp.equals(filename)){

					buf_r.close();
					if(if_newfile){
					if_newfile=false;
					return false;
					}
					else{
						break;
					}
				}
			}
			buf_r.close();
			if(if_newfile){
			FileWriter writer=new FileWriter(file_list,true);
			writer.write(filename+separater);
			writer.close();
			}
			File newfile=new File(file_path+filename+".txt");
			if(newfile.exists()){
				FileWriter writer_file=new FileWriter(newfile,false);
				writer_file.write(code);
				writer_file.close();
			}
			else{
				newfile.createNewFile();
				FileWriter writer_file=new FileWriter(newfile,false);
				writer_file.write(code);
				writer_file.close();
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;

	}

}
