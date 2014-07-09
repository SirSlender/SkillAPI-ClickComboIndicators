package com.ToA.ClickComboText;

import java.util.ArrayList;

import com.sucy.skill.click.MouseClick;

public class ClickComboHistory {

    private static final long limit = 1000;

    private final ArrayList<MouseClick> entries = new ArrayList<MouseClick>();
    private final ArrayList<String> clicks = new ArrayList<String>();
    @SuppressWarnings("unused")
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
        if (clicks.size() == 0) clicks.add(String.valueOf(click));
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
    	for (int i = 1 ; i <= clicks.size() ; i++)
    	{
    		if (i == 1)
    		{
    			if (clicks.get(0).equals("LEFT") || clicks.get(0).equals("RIGHT"))
    			{
    				concatString = clicks.get(0);
    			}
    		}
			else
			{
				if (clicks.get(i-1).equals("LEFT") || clicks.get(i-1).equals("RIGHT"))
				{
					concatString = concatString + " - " + clicks.get(i-1);
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
