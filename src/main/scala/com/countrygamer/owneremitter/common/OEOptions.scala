package com.countrygamer.owneremitter.common

import com.countrygamer.cgo.common.lib.util.Config
import com.countrygamer.cgo.wrapper.common.registries.OptionRegister
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.common.config.Configuration

/**
 *
 *
 * @author CountryGamer
 */
object OEOptions extends OptionRegister {

	var enableOwnerEmitter: Boolean = true
	var enablePlayerEmitter: Boolean = false

	override def register(): Unit = {

		OEOptions.enableOwnerEmitter = Config
				.getAndComment(this.config, Configuration.CATEGORY_GENERAL,
		            "Enable Owner Emitter",
		            "Enable the owner emitter block",
		            true
				)

		OEOptions.enablePlayerEmitter = Config
				.getAndComment(this.config, Configuration.CATEGORY_GENERAL,
		            "Enable Player Emitter",
		            "Enable the player emitter block. WARNING: UNFINISHED!",
		            true
				)

	}

	@SideOnly(Side.CLIENT)
	override def getGuiConfigClass(): Class[_ <: GuiScreen] = {
		classOf[com.countrygamer.owneremitter.client.gui.configFactory.GuiConfig]
	}

}
