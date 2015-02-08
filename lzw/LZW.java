package lzw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LZW {
	// function read from file
	public static StringBuilder readFromFile(File source) throws FileNotFoundException
	{
		if (!source.exists())
		{
			////////////////////
			return null ;
		}
	    BufferedReader reader = new BufferedReader(new FileReader(source));
	    String         line = null;
	    StringBuilder  myString = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
	    try {
			while( ( line = reader.readLine() ) != null ) 
			{
				myString.append( line );
				myString.append( ls );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return myString;
	}
	
// write on file
	public static void printOnFile(File distenation,String data) throws IOException
	{
		OutputStream out = new FileOutputStream(distenation);
		out.write(data.getBytes());
		
	}
  public static String compression(String word)
  {
      	// mkan a4el feh el dictionary 
		ArrayList<String> dictionary= new ArrayList<>();
		// el result bta3y 
        ArrayList<Integer> result= new ArrayList<>();
        
        // b3ml el dictionary bta3y
		for(int i=97;i<128;i++)
		{
			char x=(char)i;               
			String str= x + "";
			dictionary.add(str);
		}
                
		
		String current="",newAddtion="";
                
		for(int i=0 ; i < word.length();i++)
		{
			current=""+word.charAt(i);
			newAddtion = current;
			// lw el klam goz2 el gdeed elly ba5doh mwgod f el dictionary bta3y
            if(dictionary.contains(current))
                    {   
                    	 // loop 3la el goz2 el so8eer gwa el string bta3ty -mn a5r 7ta kont wa2f 3ndaha-
                        for(int j=i+1;j<word.length() ;j++)
                             {
                        	// a5od ba2y el string m3aya 7rf 7rf
                        	String st=""+word.charAt(j);
                                current+=st; 
                             // a3ml check tany 3la dictionary
		 if(dictionary.contains(current))
		 {
			 	// el klam mwgod
			 newAddtion = current;
			 // lw akbr mn elly 3ndy 7otaha f a5r el result
             if(j+1 > word.length()-1)
             {
            	 result.add(dictionary.indexOf(newAddtion)+97);
                 break;
             }
             // m4 f el a5r 
             // tmam 3eed el loop mn el awl
			continue;
		 }
		 else
		 {
			 // el gdeed m4 mwgod 5las defoh f el dictioanry 
			 dictionary.add(current);
			 // defoh f el result kman
			 result.add(dictionary.indexOf(newAddtion)+97);
			 // shoof eh el length elly 5adtoh w zwdo f el i 3l4an abd2 mn a5r mkan kont feh 
             if(dictionary.contains(current.substring(0, current.length()-1))&& current.length() > 2)
             {
            	 i += current.length()-2;
             }
			 break;
		 }
                             } 
                    }
	 
	 else
	 {
		 dictionary.add(current);
	 }
		} 
	  StringBuilder b = new StringBuilder("");

	  for(int i=0;i<result.size();i++)
	  {
		  b.append(result.get(i)+",");
	  }
      return b.toString();
  }

  
  public static String Decompression(String file)
  {
	  	String []s =file.split(",");
	  	ArrayList<Integer>compressedWord = new ArrayList<Integer>();
	  	for (int i = 0; i < s.length-1; i++) 
	  	{
			compressedWord.add(Integer.parseInt(s[i]));
	  	}
	     String result="",partOfWord="",previous="",current="";       
	     ArrayList<String> dictionary= new ArrayList<>();  
	     
	     	// creating my own dictionary 
			for(int i=97;i<128;i++)
			{
				char x=(char)i;                   
				String str= x + "";
				dictionary.add(str);
			}
	         //ba5od awl rkm f el list 3l4an a3rf awl 7rf
			previous = dictionary.get(compressedWord.get(0)-97);
	        result += previous;
	        
	      for(int i = 1 ; i< compressedWord.size() ;i++)
	      {
	    	  		// lw el rkm m4 3ndy y3ny akbr mn a5r index f el dictionary a5od elly ableh w awl 7rf mno
	                if(compressedWord.get(i)-97 >= dictionary.size())
	                {     
	                    current = previous + previous.charAt(0);
	                    result += current;
	                    previous = current ;
	                      continue;
	                }
	                // a5od a5r index f el dictionary 
	                 current = dictionary.get(compressedWord.get(i)-97);
	                // a3ml check 3la el klam dah 3ndy wala l2
	                 partOfWord = previous + current.charAt(0) ;
	                    if(!dictionary.contains(partOfWord))
	                    { 
	                    	// m4 mwgod 7otoh f el dictionary w defoh ll result 
	                    	dictionary.add(partOfWord);
	                    	result += current;
	                    	previous = current ;
	                    }
	      }	     
	     return result;
	 }
}
