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
	guiFactory = OwnerEmitter.clientProxy,
	dependencies = "required-after:Forge@[10.13,);required-after:cgo@[3.2,)"
)
object OwnerEmitter extends PluginWrapper {

	// TODO owner emitter; drop & place with owner
	// TODO player emitter; drop & place with preferred
	// TODO owner emitter; tool tip has owner

	final val pluginID = "owneremitter"
	final val pluginName = "Owner Emitter"
	final val clientProxy = "com.countrygamer.owneremitter.client.ClientProxy"
	final val serverProxy = "com.countrygamer.owneremitter.server.ServerProxy"

	@SidedProxy(
		clientSide = OwnerEmitter.clientProxy,
		serverSide = OwnerEmitter.serverProxy
	)
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
