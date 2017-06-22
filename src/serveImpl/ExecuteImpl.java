package serveImpl;

import java.rmi.RemoteException;

import service.ExecuteService;

public class ExecuteImpl implements ExecuteService {

	
	private static final int Max=300000;
	 
	@Override
	public  String execute(String code, String param) throws RemoteException {
		char values[]=new char [Max];
		int pointer=0;
		
		char instructions[];
		
		
	    char params[];
		int param_pointer=0;
		
		String result="";
		if(param!=null){
			params=param.toCharArray();
		}
		else{
			params=new char [30];
		}
		instructions=code.toCharArray();

		for(int i=0;i<instructions.length;i++){
			switch (instructions[i]) {
			case '>':
				pointer++;
				
				break;
			case '<':
				if(pointer==0){
					pointer=Max-1;
				}
				pointer--;
				break;
			case '+':
				
				values[pointer]++;
				break;
			case '-':
				if(values[pointer]==0){
					values[pointer]=256;
				}
				values[pointer]--;
				break;
			case '[':
				int count =1;
			
				if(values[pointer]==0){
					i++;
				while(true){	
					if(instructions[i]=='['){
						count++;
					}
					if(instructions[i]==']'){
						count--;
						if(count==0){
							break;
						}
					}
					i++;
				}
				
			}
				break;
			case ']':
				 int count2=1;
			if(values[pointer]!=0){
				i--;
				while(true){
					if(instructions[i]==']'){
						count2++;
					}
					if(instructions[i]=='['){
						count2--;
						if(count2==0){
							break;
						}
					}
					i--;
				}
			}
				break;
			case ',':
				values[pointer]=params[param_pointer];
				param_pointer++;
				break;
			case '.':
				result+=values[pointer];
				break;

			default:
				break;
			}
		}
		return result;
	}

}
