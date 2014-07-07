package com.ToA.ClickComboText;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.sucy.skill.click.MouseClick;

public class ClickComboText extends JavaPlugin implements Listener
{
	private final HashMap<String, ClickComboHistory> history = new HashMap<String, ClickComboHistory>();
	
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getLogger().info("ClickComboIndicators for SkillAPI has been enabled!");
	}
	
	@Override
	public void onDisable()
	{
		Bukkit.getLogger().info("ClickComboIndicators for SkillAPI has been disabled!");
	}
    
    @EventHandler
    public void clickComboText(PlayerInteractEvent e)
    {
    	Player p = e.getPlayer();
        if (!history.containsKey(e.getPlayer().getName())) {
            history.put(e.getPlayer().getName(), new ClickComboHistory(this));
        }
        ClickComboHistory pch = history.get(e.getPlayer().getName());
    	ItemMessage im = new ItemMessage(this);
    	try{
	    	if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) 
	    	{
	        	pch.addClick(MouseClick.LEFT);
	        	im.sendMessage(p, pch.concatStrings(), 1);
	        	if (pch.clickSize() >= 4)
	        	{
	        		pch.clearClicks();
	        	}
	        	e.setCancelled(true);
	    	}
	    	else if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
	    		pch.addClick(MouseClick.RIGHT);
	        	im.sendMessage(p, pch.concatStrings(), 1);
	        	if (pch.clickSize() >= 4)
	        	{
	        		pch.clearClicks();
	        	}
	        	e.setCancelled(true);
			}
    	}
    	catch (NullPointerException e2)
    	{
    		e2.printStackTrace();
    	}
    }

    @EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        history.remove(event.getPlayer().getName());
    }

}
