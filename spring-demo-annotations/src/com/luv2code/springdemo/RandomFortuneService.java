package com.luv2code.springdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
	// create an array of string from file
	private List<String> theFortunes;
	
	public RandomFortuneService() throws IOException {
		URL url = getClass().getResource("fortune-data.txt");
		File file = new File(url.getFile());
		FileInputStream fin = new FileInputStream(file.getAbsolutePath());
	    BufferedReader br = new BufferedReader(
	        new InputStreamReader(fin, "utf-8"));
	    
	    theFortunes = new ArrayList<>();
	    try {
	        String line;
	        while ((line = br.readLine()) != null) {
	          String fortune = new String(line.trim().getBytes(), "UTF-8");
	          theFortunes.add(fortune);
	        }
	      } finally {
	          br.close();
	      }
	    
	}
	// create a random number generator
	private Random num = new Random();
	
	@Override
	public String getFortune() {
		// pick a random string from the array
		int index = num.nextInt(theFortunes.size());
		
		String theFortune = theFortunes.get(index);
		
		return theFortune;
		
	}

}
