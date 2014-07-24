package com.countrygamer.owneremitter.common.block

import com.countrygamer.cgo.wrapper.common.registries.BlockRegister
import com.countrygamer.owneremitter.common.OwnerEmitter
import com.countrygamer.owneremitter.common.tile.TEEmitter
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

	override def registerTileEntities: Unit = {

		GameRegistry
				.registerTileEntity(classOf[TEEmitter], OwnerEmitter.pluginID + "_Owner Emitter")

	}

	override def register(): Unit = {

		OEBlocks.ownerEmitter = new BlockOwnerEmitter(OwnerEmitter.pluginID, "Owner Emitter")
		OEBlocks.ownerEmitter.setCreativeTab(CreativeTabs.tabRedstone)

	}

	override def registerCrafting: Unit = {

	}

	override def registerSmelting: Unit = {

	}

	override def registerOther: Unit = {

	}

}
