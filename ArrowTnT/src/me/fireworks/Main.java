package me.fireworks;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;





public class Main extends JavaPlugin implements Listener{
	
	private boolean clearBlocks;

	public void onEnable(){
		PluginManager pluginManager  = getServer().getPluginManager();
		pluginManager.registerEvents(this, this);
	}
	
	
	@EventHandler
    public void blank(PlayerInteractEvent event){
		
          //Player p = event.getPlayer();
          //p.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 4);
        
          
	}
            

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onProjectileHit(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
        if((entity instanceof Trident)) {
            Trident trident = (Trident) entity;
            Entity shooter = (Entity) trident.getShooter();
            if((shooter instanceof Player)) {
                noBlockExplosion(trident.getLocation(), 5.0F);
                trident.remove();
            }
        }
        
        
        if((entity instanceof Arrow)) {
            Arrow arrow = (Arrow) entity;
            Entity shooter = (Entity) arrow.getShooter();
            if((shooter instanceof Player)) {
                noBlockExplosion(arrow.getLocation(), 5.0F);
                arrow.remove();
            }
        }
        
        
        
    }
 
    public void noBlockExplosion(Location location, float force) {
        clearBlocks = true;
        location.getWorld().createExplosion(location, force);
        clearBlocks = false;
    }
 
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onEntityExplode(EntityExplodeEvent event) {
        if(clearBlocks) event.blockList().clear();
    }


	
            

}