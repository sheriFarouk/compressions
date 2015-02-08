/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UniformQuantizer;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Shorouk
 */
public class ImageRW 
{	
    public static int width=0;
    public static int height=0;
    public static int[][] readImage(String filePath)
    {
        File file=new File(filePath);
        BufferedImage image=null;
        try
        {
            image=ImageIO.read(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

          width=image.getWidth();
          height=image.getHeight();
        int[][] pixels=new int[height][width];

        for(int x=0;x<width;x++)
        {
            for(int y=0;y<height;y++)
            {
                int rgb=image.getRGB(x, y);
                int alpha=(rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb >> 0) & 0xff;

                pixels[y][x]=r;
                //System.out.println(pixels[y][x]);
            }
        }

        return pixels;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void compress(String photoPath,String outPut,int quantizer)
    {
		double MSE=0;
		int array[][]=ImageRW.readImage(photoPath);
		int levels=(int) Math.pow(2, quantizer);
		int step=256/levels;
		
		 File compFile = new File(outPut+"\\compresed.txt");
		   FileOutputStream fos = null;
			ObjectOutputStream oos = null;

			try 
			{
				
				fos = new FileOutputStream(compFile);
				oos = new ObjectOutputStream(fos);
				 
				oos.writeInt(quantizer);
				oos.writeInt(height);
				oos.writeInt(width);
				for(int i=0;i<height;i++)
				{
					for(int j=0;j<width;j++)
					{
						int qInverse =(array[i][j]/step);
						qInverse *= step;
						qInverse += (step/2);
						
						MSE += Math.pow(qInverse - array[i][j], 2);
						array[i][j]/=step;				//get level
						oos.writeInt(array[i][j]);  	//write level
					}
				}
			MSE/=(height*width);	
			JOptionPane.showMessageDialog(null, "MSE = "+MSE, "MSE  ", JOptionPane.INFORMATION_MESSAGE);
				oos.close();
				fos.close();
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void deCompress(String inputFile,String outPut)
    {
    	File compFile = new File(inputFile);
		FileInputStream fos = null;
		ObjectInputStream oos = null;
		int levels=0 ,step =0,quantizer=0 ;
		
		int [][]array= null;
			try 
			{
				fos = new FileInputStream(compFile);
				oos = new ObjectInputStream(fos);
				
				quantizer = oos.readInt();			//
				height = oos.readInt();
				width = oos.readInt();
				
				step = 256/((int) Math.pow(2, quantizer));
				array = new int[height][width];
				
				for(int i=0;i<height;i++)
				{
					for(int j=0;j<width;j++)
					{		
						array[i][j] = oos.readInt();  	
						array[i][j] *= step;
						array[i][j] += (step/2);
					}
				}				
				//System.out.println("array read "+ array[0][0]);
				oos.close();
				fos.close();
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writeImage(array, outPut+"\\decompressed.jpg");
			System.out.println("done");
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public static void writeImage(int[][] pixels,String outputFilePath)
    {
        File fileout=new File(outputFilePath);
        BufferedImage image2=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB );

        for(int x=0;x<width ;x++)
        {
            for(int y=0;y<height;y++)
            {
                image2.setRGB(x,y,(pixels[y][x]<<16)|(pixels[y][x]<<8)|(pixels[y][x]));
            }
        }
        try
        {
            ImageIO.write(image2, "jpg", fileout);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) 
    {
    	//compress("E:\\java\\myQuantizer\\lena.jpg", "E:\\java\\myQuantizer", 3);
       // deCompress("E:\\java\\myQuantizer\\compresed.txt", "E:\\java\\myQuantizer");
    }

}

