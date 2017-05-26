package tenisz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB{

	public DB (){
		
	}
	
	public static Connection connect() throws Exception{
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/tennisresult";
			String username = "root";
			String password = "password";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
		}catch(Exception e){System.out.println(e);}
		
		return null;
		
	}
		
	public void save(String player1Name, String player2Name, int player1Score, int player2Score) throws Exception{
		String name1;
		String name2;
		int score1;
		int score2;
		
		int compare = player1Name.compareToIgnoreCase(player2Name);
		if(compare <= 0){
			name1 = player1Name;
			name2 = player2Name;
			score1 = player1Score;
			score2 = player2Score;			
		}
		else{
			name1 = player2Name;
			name2 = player1Name;
			score1 = player2Score;
			score2 = player1Score;
		}
		Connection con = null;
		try {
			con = connect();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS result(id int NOT NULL AUTO_INCREMENT, player1Name varchar(255), player2Name varchar(255), player1Score int NOT NULL, player2Score int NOT NULL, PRIMARY KEY(id))");
			create.executeUpdate();
			System.out.println("Table create");
			create.close();
		}catch(Exception e){System.out.println(e);}
		
		int record = searchRecord(name1, name2);
		
		if(record == 0){
			try{
				PreparedStatement post = con.prepareStatement("INSERT INTO result (player1Name, player2name, player1Score, player2Score) VALUES ('"+name1+"','"+name2+"', '"+score1+"', '"+score2+"')");
				post.executeUpdate();
				System.out.println("Insert complete");
				post.close();
			}catch(Exception e){System.out.println(e);}
		}
		else{
			try{
				PreparedStatement post = con.prepareStatement("UPDATE result SET player1Name = '"+name1+"', player2Name = '"+name2+"', player1Score = '"+score1+"', player2Score = '"+score2+"' WHERE id = '"+record+"'");
				post.executeUpdate();
				System.out.println("Update complete");
				post.close();
			}catch(Exception e){System.out.println(e);}
		}
		con.close();
		
	}
	
	public int searchRecord(String player1Name, String player2Name) throws Exception{
		Connection con = null;
		PreparedStatement result_mysql = null;
		ResultSet res = null;
		int result = 0;
		String name1;
		String name2;
		
		int compare = player1Name.compareToIgnoreCase(player2Name);
		if(compare <= 0){
			name1 = player1Name;
			name2 = player2Name;
		}
		else{
			name1 = player2Name;
			name2 = player1Name;
		}
		
		try {
			con = connect();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			result_mysql = con.prepareStatement("SELECT id FROM result WHERE player1Name = '"+name1+"' AND player2Name = '"+name2+"'");
			res = result_mysql.executeQuery();
			
			if(!res.next()){
				result = 0;
				System.out.println(result);
			}
			
			res.beforeFirst();
			
			while(res.next()){
				result = res.getInt("id");
				System.out.println(result);
			}
		}catch(Exception e){System.out.println(e);}
		
		result_mysql.close();
		res.close();
		con.close();
		return result;
	}

	public int getPlayer1Score(int record) throws Exception{
		int score = 0;
		
		Connection con = null;
		PreparedStatement result_mysql = null;
		ResultSet res = null;
		int result = 0;
		
		try {
			con = connect();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			result_mysql = con.prepareStatement("SELECT player1Score FROM result WHERE id = '"+record+"' ");
			res = result_mysql.executeQuery();
			
			if(!res.next()){
				result = 0;
				System.out.println(result);
			}
			
			res.beforeFirst();
			
			while(res.next()){
				result = res.getInt("player1Score");
				System.out.println(result);
			}
		}catch(Exception e){System.out.println(e);}
		
		result_mysql.close();
		res.close();
		con.close();
		
		return score;
	}

	public int getPlayer2Score(int record) throws Exception{
		int score = 0;
		
		Connection con = null;
		PreparedStatement result_mysql = null;
		ResultSet res = null;
		int result = 0;
		
		try {
			con = connect();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			result_mysql = con.prepareStatement("SELECT player2Score FROM result WHERE id = '"+record+"' ");
			res = result_mysql.executeQuery();
			
			if(!res.next()){
				result = 0;
				System.out.println(result);
			}
			
			res.beforeFirst();
			
			while(res.next()){
				result = res.getInt("player2Score");
				System.out.println(result);
			}
		}catch(Exception e){System.out.println(e);}
		
		result_mysql.close();
		res.close();
		con.close();
		
		return score;
	}



	
	

	
	
	

}
