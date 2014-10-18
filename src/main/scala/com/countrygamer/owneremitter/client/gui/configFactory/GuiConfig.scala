package com.countrygamer.owneremitter.client.gui.configFactory

import com.countrygamer.cgo.wrapper.client.gui.configFactory.GuiConfigWrapper
import com.countrygamer.owneremitter.common.OwnerEmitter
import net.minecraft.client.gui.GuiScreen

/**
 *
 *
 * @author CountryGamer
 */
class GuiConfig(guiScreen: GuiScreen)
		extends GuiConfigWrapper(guiScreen, OwnerEmitter, OwnerEmitter.pluginID) {

}
