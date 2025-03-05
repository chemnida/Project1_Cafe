package com.shop.cafe.dao;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.shop.cafe.dto.Product;

@Component
public class ProductDao {
	
	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USER;
	
	@Value("${spring.datasource.password}")
	private String DB_PW;
	
	public List<Product> getAllProducts() throws Exception {
		//JDBC 6단계

		//1. 드라이버 등록
		Class.forName(DB_DRIVER);
		
		String sql = "select * from product";
		
		try(
				//2. 연결
				Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
				
				//3.Statement생성
				PreparedStatement stmt = con.prepareStatement(sql);
				
				//4.SQL전송
				ResultSet rs = stmt.executeQuery(); 
			) {
			
			//5.결과받기
			List<Product> list = new ArrayList();
			while(rs.next()) {
				int prodcode = rs.getInt("prodcode");
				String prodname = rs.getString("prodName");
				String pimg = rs.getString("pimg");
				int price = rs.getInt("price");
				
				list.add(new Product(prodcode, price, prodname, pimg));
				
			}
			return list;
			
		}
		
	}
	
}
