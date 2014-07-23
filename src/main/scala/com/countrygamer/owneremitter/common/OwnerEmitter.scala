package com.countrygamer.owneremitter.common

import com.countrygamer.cgo.wrapper.common.{PluginWrapper, ProxyWrapper}
import cpw.mods.fml.common.{Mod, SidedProxy}

/**
 *
 *
 * @author CountryGamer
 */
@Mod(modid = OwnerEmitter.pluginID, name = OwnerEmitter.pluginName, version = "@PLUGIN_VERSION@",
	modLanguage = "scala",
	guiFactory = "com.countrygamer.owneremitter.client.gui.configFactor.OEFactory")
object OwnerEmitter extends PluginWrapper {

	final val pluginID = "owneremitter"
	final val pluginName = "Owner Emitter"

	@SidedProxy(clientSide = "com.countrygamer.client.ClientProxy",
		serverSide = "com.countrygamer.common.CommonProxy")
	var proxy: ProxyWrapper = null

}
