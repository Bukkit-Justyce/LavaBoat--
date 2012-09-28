package org.lavaboat;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.*;
import org.bukkit.util.Vector;

public class LBvehiclelistener implements Listener {
	public static LavaBoat plugin;
    public LBvehiclelistener(LavaBoat instance) {
		plugin = instance;
    }

    @EventHandler
    public void  onVehicleLoseLife(VehicleDamageEvent Event){
    	Vehicle v = Event.getVehicle();
    	Boolean use = Boolean.parseBoolean(LavaBoat.config.getWorld(v.getWorld().getName()));//getting if it's enabled in this world
		if(use && v instanceof Vehicle && !(Event.getAttacker() instanceof Player)){
			Event.setCancelled(true);
			v.setFireTicks(0);
		}
    	
    }
    @EventHandler
    public void onVehicleMove(VehicleMoveEvent e){
    	Vehicle v = e.getVehicle();
    	Boolean use = Boolean.parseBoolean(LavaBoat.config.getWorld(v.getWorld().getName()));//getting if it's enabled in this world
		if(use && v instanceof Boat || v instanceof Minecart){
				Vector vect = v.getVelocity();
    			Material mat=v.getLocation().getWorld().getBlockAt(e.getVehicle().getLocation()).getType();
    			if(mat==Material.LAVA||mat==Material.STATIONARY_LAVA){
    				vect.setY(0.35);	//the vehicle will float on the lava
    			}
    			v.setVelocity(vect);
    	}
    }
}
