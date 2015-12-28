package clover.client.gui;

import clover.common.util.Config;
import clover.common.util.References;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.Iterator;
import java.util.List;

public class GuiModConfig extends GuiConfig
{
	public GuiModConfig(GuiScreen parentScreen)
	{
		super(parentScreen, new ConfigElement(Config.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), References.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(Config.config.toString()));
	}

	// Very hacky solution for defaults
	@Override
	public void drawToolTip(List stringList, int x, int y)
	{
		Iterator<String> iter = stringList.iterator();

		while (iter.hasNext())
			if (iter.next().startsWith(EnumChatFormatting.AQUA + ""))
				iter.remove();

		super.drawToolTip(stringList, x, y);
	}
}