package com.countrygamer.owneremitter.common

import com.countrygamer.cgo.wrapper.common.{PluginWrapper, ProxyWrapper}
import com.countrygamer.owneremitter.common.block.OEBlocks
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.{Mod, SidedProxy}

/**
 *
 *
 * @author CountryGamer
 */
@Mod(modid = OwnerEmitter.pluginID, name = OwnerEmitter.pluginName, version = "@PLUGIN_VERSION@",
	modLanguage = "scala",
	guiFactory = "com.countrygamer.owneremitter.client.gui.configFactory.OEFactory")
object OwnerEmitter extends PluginWrapper {

	final val pluginID = "owneremitter"
	final val pluginName = "Owner Emitter"

	@SidedProxy(clientSide = "com.countrygamer.owneremitter.client.ClientProxy",
		serverSide = "com.countrygamer.owneremitter.common.CommonProxy")
	var proxy: ProxyWrapper = null

	@Mod.EventHandler
	def preInit(event: FMLPreInitializationEvent): Unit = {
		super.preInitialize(this.pluginID, this.pluginName, event, this.proxy, OEBlocks)

	}

	@Mod.EventHandler
	def init(event: FMLInitializationEvent): Unit = {
		super.initialize(event)

	}

	@Mod.EventHandler
	def postInit(event: FMLPostInitializationEvent): Unit = {
		super.postInitialize(event)

	}

}
