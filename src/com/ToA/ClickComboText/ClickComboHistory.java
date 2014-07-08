package com.ToA.ClickComboText;

import java.util.ArrayList;

import com.sucy.skill.click.MouseClick;

public class ClickComboHistory {

    private static final long limit = 1000;

    private final ArrayList<MouseClick> entries = new ArrayList<MouseClick>();
    private final ArrayList<String> clicks = new ArrayList<String>();
    private final ClickComboText cct;
    
    private long timer;
    
    public ClickComboHistory(ClickComboText plugin)
    {
    	this.cct = plugin;
    }
    
    public void addClick(MouseClick click)
    {
    	entries.add(click);
    	clicks.add(String.valueOf(click));
    	updateEntries();
        if (entries.size() == 0) entries.add(click);
    }
    
    private void updateEntries()
    {

        if (entries.size() > 0 && System.currentTimeMillis() - timer > limit) {
            entries.clear();
            clicks.clear();
        }
        else if (entries.size() == 4)
        {
        	entries.clear();
        }
        timer = System.currentTimeMillis();
    }
    
    public String concatStrings()
    {
    	String concatString = null;
    	for (int i = 0 ; i < clicks.size() ; i++)
    	{
    		if (i == 0)
    		{
    			if (!clicks.get(i).isEmpty())
    			{
	    			if (clicks.get(0).equals("LEFT") || clicks.get(0).equals("RIGHT"))
	    			{
	    				concatString = clicks.get(0);
	    			}
    			else
    			{ 
    				concatString = null;
    			}
    			}
    		}
			else
			{
				if (clicks.get(i).equals("LEFT") || clicks.get(i).equals("RIGHT"))
				{
					concatString = concatString + " - " + clicks.get(i);
				}
			}
    	}
    	return concatString;
    }
    
    public void clearClicks()
    {
    	clicks.clear();
    }
    
    public int clickSize()
    {
    	return clicks.size();
    }

}
