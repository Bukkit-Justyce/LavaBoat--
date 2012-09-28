package org.lavaboat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class LBConfig {
	File f= new File("plugins/LavaBoat/World.txt");
	PrintWriter out ;
    public static LavaBoat plugin;

	public LBConfig(LavaBoat instance){
		plugin=instance;
		//creating the file if it doesn't exist
		if (!f.exists()){
			new File("plugins/LavaBoat").mkdir();
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out = new PrintWriter(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println("# This is the LavaBoat+ config file for worlds.");
			out.println("# Define your worlds in which LavaBoat+ is enabled below :");
			out.println("world=true");
			out.println("AnotherWorldNameHere=true");
			out.close();
		}
	}
	public void addWorld(String w){
		try {
			out = new PrintWriter(new FileWriter(f,true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println(w+"=true");
		out.close();
	}
	public void removeWorld(String world){
		Properties prop = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(f);
	         prop.load(in); //loads the file contents of worlds ("in" which references to the worlds file) from the input stream.
	      	if(world.equals(world)) {
	      		String WN = prop.getProperty(world);
		      	in.close();
		      	if(WN==null){ return;}
		      	prop.remove(world);
		      	try {
					out = new PrintWriter(f);
					prop.store(out, "");
				} catch (IOException e) {
					e.printStackTrace();
				}
	      	}
	      	in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getWorld(String world) {	
		Properties prop = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(f);
	         prop.load(in); //loads the file contents of worlds ("in" which references to the worlds file) from the input stream.
	      	if(world.equals(world)) {
	      		String WN = prop.getProperty(world);
		      	in.close();
	      		return WN;
	      	}
	      	in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
     	return plugin.pref+"Error while reading World.txt!";
	}
}
