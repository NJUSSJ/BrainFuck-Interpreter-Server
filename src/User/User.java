package User;

public class User {

	private String username;
	private String password;
	
	public User(String username) {
		// TODO 自动生成的构造函数存根
		this.username=username;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getPassword(){
		return password;
	}
}
