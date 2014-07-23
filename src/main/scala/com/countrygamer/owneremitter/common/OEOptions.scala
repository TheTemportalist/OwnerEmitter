package com.countrygamer.owneremitter.common

import com.countrygamer.cgo.wrapper.common.registries.OptionRegister
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.gui.GuiScreen

/**
 *
 *
 * @author CountryGamer
 */
object OEOptions extends OptionRegister {

	override def register(): Unit = {

	}

	@SideOnly(Side.CLIENT)
	override def getGuiConfigClass(): Class[_ <: GuiScreen] = {
		null
	}

}
