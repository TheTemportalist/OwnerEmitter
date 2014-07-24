package com.countrygamer.owneremitter.common.block

import com.countrygamer.cgo.wrapper.common.registries.BlockRegister
import com.countrygamer.owneremitter.common.OwnerEmitter
import com.countrygamer.owneremitter.common.item.IBOwnerEmitter
import com.countrygamer.owneremitter.common.tile.{TEOwnerEmitter, TEPlayerEmitter}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

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
			classOf[IBOwnerEmitter], classOf[TEOwnerEmitter]) {

			override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int,
					entity: EntityLivingBase, itemStack: ItemStack): Unit = {
				super.onBlockPlacedBy(world, x, y, z, entity, itemStack)

				val tileEntity: TileEntity = world.getTileEntity(x, y, z)
				tileEntity match {
					case emitter: TEOwnerEmitter =>
						entity match {
							case player: EntityPlayer =>
								emitter.setOwner(player)

							case _ =>

						}
					case _ =>
				}
			}

		}
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
