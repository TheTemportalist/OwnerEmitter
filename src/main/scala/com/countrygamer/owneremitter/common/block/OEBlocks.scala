package com.countrygamer.owneremitter.common.block

import com.countrygamer.cgo.library.common.register.BlockRegister
import com.countrygamer.owneremitter.common.OwnerEmitter
import com.countrygamer.owneremitter.common.tile.{TEOwnerEmitter, TEPlayerEmitter}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs

/**
 *
 *
 * @author CountryGamer
 */
object OEBlocks extends BlockRegister {

	var ownerEmitter: Block = null
	var playerEmitter: Block = null

	override def registerTileEntities: Unit = {

		GameRegistry.registerTileEntity(classOf[TEPlayerEmitter],
			OwnerEmitter.pluginID + "_PlayerEmitter")
		GameRegistry.registerTileEntity(classOf[TEOwnerEmitter],
			OwnerEmitter.pluginID + "_OwnerEmitter")

	}

	override def register(): Unit = {

		OEBlocks.ownerEmitter = new BlockEmitter(OwnerEmitter.pluginID, "Owner Emitter",
			classOf[TEOwnerEmitter])
		OEBlocks.ownerEmitter.setCreativeTab(CreativeTabs.tabRedstone)

		OEBlocks.playerEmitter = new BlockEmitter(OwnerEmitter.pluginID, "Player Emitter",
			classOf[TEPlayerEmitter])
		OEBlocks.playerEmitter.setCreativeTab(CreativeTabs.tabRedstone)

	}

	override def registerCrafting: Unit = {

	}

	override def registerSmelting: Unit = {

	}

	override def registerOther: Unit = {

	}

}
