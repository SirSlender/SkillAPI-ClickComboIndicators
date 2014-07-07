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

public class ClickComboText extends JavaPlugin implements Listener
{
	private final HashMap<String, Integer> history = new HashMap<String, Integer>();
	
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
    	p.sendMessage("clickComboText Event fired!");
    	final HashMap<String, String> click = new HashMap<String, String>();
    	int ca = clickAmount(p);
    	String pName = p.getName();
    	ItemMessage im = new ItemMessage(this);
    	p.sendMessage("Variables initialized!");
    	try{
    	if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
        	p.sendMessage("LEFT-CLICK");
    		if (ca == 0)
    		{
    			click.put(pName, "LEFT");
    			String playerClick = click.get(pName);
    			im.sendMessage(p, playerClick, 2);
    			addClicks(p);
    			e.setCancelled(true);
    		}
    		else if (ca > 0 && ca < 5)
    		{
    			String prevClicks = click.get(pName);
    			String newClicks = prevClicks + " - " + "LEFT";
    			click.put(pName, newClicks);
    			im.sendMessage(p, newClicks, 2);
    			addClicks(p);
    			int newCA = clickAmount(p);
    			if (newCA == 4)
    			{
    				click.remove(pName);
    			}
    			e.setCancelled(true);
    		}
    		else
    		{
    			click.put(pName, "LEFT");
    			String playerClick = click.get(pName);
    			im.sendMessage(p, playerClick, 2);
    			setClick(p, 1);
    			e.setCancelled(true);
    		}
    	}
    	else if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
    		if (ca == 0)
    		{
    			click.put(pName, "RIGHT");
    			String playerClick = click.get(pName);
    			im.sendMessage(p, playerClick, 2);
    			addClicks(p);
    			e.setCancelled(true);
    		}
    		else if (ca > 0 && ca < 5)
    		{
    			String prevClicks = click.get(pName);
    			String newClicks = prevClicks + " - " + "RIGHT";
    			click.put(pName, newClicks);
    			im.sendMessage(p, newClicks, 2);
    			addClicks(p);
    			int newCA = clickAmount(p);
    			if (newCA == 4)
    			{
    				click.remove(pName);
    			}
    			e.setCancelled(true);
    		}
    		else
    		{
    			click.put(pName, "RIGHT");
    			String playerClick = click.get(pName);
    			im.sendMessage(p, playerClick, 2);
    			setClick(p, 1);
    			e.setCancelled(true);
    		}
		}
    	}
    	catch (NullPointerException e2)
    	{
    		e2.printStackTrace();
    	}
    }
    
    public int clickAmount(Player player)
    {
    	int i;
    	Player p = player;
    	String pName = p.getName();
    	if (history.containsKey(pName))
    	{
    		i = history.get(pName);
    		if (i >= 4)
    		{
    			i = 0;
    		}
    	}
    	else 
    	{
    		i = 0;
    	}
    	return i;
    	
    }
    
    public void addClicks(Player player)
    {
    	Player p = player;
    	String pName = p.getName();
    	if (history.containsKey(pName))
    	{
    		history.put(pName, history.get(pName) + 1);
    	}
    	else
    	{
    		history.put(pName, 1);
    	}
    }
    
    public void setClick(Player player, int amount)
    {
    	String pName = player.getName();
    	history.put(pName, amount);
    }

    @EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        history.remove(event.getPlayer().getName());
    }

}
