package net.zaizheli.web.utils;


import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {
   
   // private static final String salt = KeyGenerators.string().generateKey(); 
	private static final String salt = "0b571cea7d3d4f9d";
    private static final StandardPasswordEncoder encoder = new StandardPasswordEncoder(salt);
 
    public  String encrypt(String rawPassword) {
         return encoder.encode(rawPassword);
    }
 
    public  StandardPasswordEncoder getEncoder() {
		return encoder;
	}

	public  boolean match(String rawPassword, String password) {
         return encoder.matches(rawPassword, password);
    }
 }

