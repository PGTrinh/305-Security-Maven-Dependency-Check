package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger; 
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
@RestController
class ServerController{
//FIXME:  Add hash function to return the checksum value for the data string that should contain your name.
	//handling the checked exception for message digest
public static String calculateHash(String name) throws NoSuchAlgorithmException {  
	//reference https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm
	MessageDigest md = MessageDigest.getInstance("SHA-256");  
	//reference hash byte https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha-256-in-java
	byte[] hash =  md.digest(name.getBytes(StandardCharsets.UTF_8));
	//reference for hash SHA-256 https://www.geeksforgeeks.org/sha-256-hash-in-java/#
    BigInteger number = new BigInteger(1, hash);  
    StringBuilder hexString = new StringBuilder(number.toString(16));  
    while (hexString.length() < 32)  
    {  
        hexString.insert(0, '0');  
    }  
    return hexString.toString();   
} 
	@RequestMapping("/hash")
//reference my hash and handling the checked exception for message digest
	public String myHash() throws NoSuchAlgorithmException{

		String data = "Hello Philip Trinh!"; //default string data with my first and last name
		String hash = calculateHash(data); //call method convert of hash data via SHA-256
       
		return "<p>data:"+data+" Algorithm: SHA-256 "+" : "+hash; //return data that is converted
    }
}
