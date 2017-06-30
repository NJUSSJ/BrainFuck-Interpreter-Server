package serveImpl;

import java.rmi.RemoteException;

import service.ExecuteService;

public class ExecuteImpl implements ExecuteService {


	private static final int Max=300000;

	@Override
	public  String execute(String code, String param) throws RemoteException {


		//Decide bf or Ook.
		int sign=code.indexOf("O");
		if(sign>=0){

			String[] temp_codes=code.split(" ");
			String[] codes=new String[temp_codes.length/2];
			String code_bf="";
			for(int i=0;i<codes.length;i++ ){
				codes[i]=temp_codes[2*i]+temp_codes[2*i+1];

				if(codes[i].equals("Ook.Ook?")){
					codes[i]=">";
				}
				else if(codes[i].equals("Ook?Ook.")){
					codes[i]="<";
				}
				else if(codes[i].equals("Ook.Ook.")){
					codes[i]="+";
				}
				else if(codes[i].equals("Ook!Ook!")){
					codes[i]="-";
				}
				else if(codes[i].equals("Ook!Ook.")){
					codes[i]=".";
				}
				else if(codes[i].equals("Ook.Ook!")){
					codes[i]=",";
				}
				else if(codes[i].equals("Ook!Ook?")){
					codes[i]="[";
				}
				else if(codes[i].equals("Ook?Ook!")){
					codes[i]="]";
				}
				code_bf+=codes[i];
			}
			code=code_bf;
		}


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
