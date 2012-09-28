package org.lavaboat;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

public class LBPlayerListener implements Listener
{
	LavaBoat plugin;
	public LBPlayerListener(LavaBoat plugin)
	{
		this.plugin=plugin;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerToggleSneak(PlayerToggleSneakEvent e){
		Player p=e.getPlayer();
		Boolean use = Boolean.parseBoolean(LavaBoat.config.getWorld(p.getWorld().getName()));//getting if it's enabled in this world
		if(use){
			if(p.getVehicle() instanceof Minecart && plugin.canUseM(p)){
				Vehicle v =(Vehicle) p.getVehicle();
				Material mat=v.getLocation().getWorld().getBlockAt(v.getLocation()).getType();
    			if(mat==Material.LAVA||mat==Material.STATIONARY_LAVA){
    				Vector l=p.getLocation().getDirection();
    				l.setY(0.35);
    				v.setVelocity(l);		//use to drive minecarts on lava
    			}
    		}
			/*if(p.getVehicle() instanceof Pig && plugin.canUseP(p)){
				Vehicle v =(Vehicle) p.getVehicle();
    			Vector l=p.getLocation().getDirection();
    			v.setVelocity(l);		//use to drive pigs
    		}*/
		}
	}
	//  lavaboat.use.pigs:
   // description: allow to use pigs
	 @EventHandler(priority = EventPriority.HIGH)
		public void onPlayerMove(PlayerMoveEvent e){
			Player p=e.getPlayer();
			Boolean use = Boolean.parseBoolean(LavaBoat.config.getWorld(p.getWorld().getName()));//getting if it's enabled in this world
			Location loc = p.getLocation();
			if(use && plugin.canWalk(p)){
				Material mat=loc.getWorld().getBlockAt(loc).getType();
    			if(mat==Material.LAVA||mat==Material.STATIONARY_LAVA){
    				p.setVelocity(new Vector(p.getVelocity().getX(),0.3,p.getVelocity().getZ()));//use to make the player float on lava but not realistic
    			}
    			p.setFireTicks(0);
			}
		}
}
