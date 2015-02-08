package lzw;

import java.util.ArrayList;

public class Mainclass {
    
    public static void main(String []args){
     
    	String c=LZW.compression("abaababbaabaabaaaababbbbbbbb");
    	System.out.println(LZW.compression("abaababbaabaabaaaababbbbbbbb")); 
    	System.out.println();
    	System.out.println(LZW.Decompression(c));
    }
}
