package lz77;


public class lz7 
{
	public static String Compress (String file)
	{
		StringBuilder result = new StringBuilder("");
		int pointer=0,length=0;
		String nextChar="" , current="" , previous="";
		for(int i=0 ;i<file.length();i++)
		{

			previous = file.substring(0, i);
			current = file.substring(i, i+1);

			boolean excidSize = true;

			for(int j=i;j<file.length();j++)
			{
				current = file.substring(i, j+1);
				if(!previous.contains(current))
				{
					excidSize = false;
					break;
				}
				else if(( i - previous.lastIndexOf(current))>15)
				{
					excidSize = false;
					break;
				}
			}
			//System.out.println("_"+current+"_"+previous+"_");
			if (excidSize == true)
			{
				length = current.length();
				nextChar = "null";
				pointer = i - previous.lastIndexOf(current);
			}
			else
			{
				nextChar = "" + current.charAt(current.length()-1);
				length = current.length()-1;
				pointer = i - previous.lastIndexOf(current.substring(0, current.length()-1));
			}
			//System.out.println(pointer);
			result.append("<"+pointer+","+length+","+nextChar+">,");
			i += length; 
		}
		result.setLength(result.length()-1);
		return result.toString();
	}
	///////////////////////////////////////////////////
	public static String deCompress (String file)
	{
		StringBuilder result = new StringBuilder("");
		
		int length=0 , pointer=0;
		String nextChar="",current="";
		
		file=file.replaceAll("<", "");
		file=file.replaceAll(">", "");
		
		String []arr = file.split(",");
		
		for(int i=0; i<arr.length; i+=3)
		{
			//System.out.println(i+arr[i]);
			pointer = Integer.parseInt(arr[i]);
			length = Integer.parseInt(arr[i+1]);
			nextChar = arr[i+2];
			
			current = result.substring(result.length()-pointer, result.length()-pointer+length );
			result.append(current);
			//System.out.println(nextChar);
			if(! nextChar.equals("null") )
			{
				result.append(nextChar);
			}
		}
		
		
		
		
		return result.toString();
				
	}
	
	
}
