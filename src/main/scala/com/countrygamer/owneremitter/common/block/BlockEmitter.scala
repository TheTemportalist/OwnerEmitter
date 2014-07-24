package com.countrygamer.owneremitter.common.block

import com.countrygamer.cgo.wrapper.common.block.BlockWrapperTE
import com.countrygamer.owneremitter.common.tile.{TEEmitter, TEOwnerEmitter, TEPlayerEmitter}
import net.minecraft.block.material.Material
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{ItemBlock, ItemStack}
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.{IBlockAccess, World}

/**
 *
 *
 * @author CountryGamer
 */
class BlockEmitter(pluginID: String, name: String, itemBlock: Class[_ <: ItemBlock],
		tileEntityClass: Class[_ <: TileEntity])
		extends BlockWrapperTE(Material.rock, pluginID, name, itemBlock, tileEntityClass) {

	// Default Constructor
	{

	}

	// End Constructor

	def this(pluginID: String, name: String, tileEntityClass: Class[_ <: TileEntity]) {
		this(pluginID, name, null, tileEntityClass)

	}

	override def canProvidePower: Boolean = {
		true
	}

	override def isProvidingWeakPower(world: IBlockAccess, x: Int, y: Int, z: Int,
			side: Int): Int = {
		val tileEntity: TileEntity = world.getTileEntity(x, y, z)
		if (tileEntity != null && tileEntity.isInstanceOf[TEEmitter])
			return tileEntity.asInstanceOf[TEEmitter].getRedstonePower
		0
	}

	override def isProvidingStrongPower(world: IBlockAccess, x: Int, y: Int, z: Int,
			side: Int): Int = {
		this.isProvidingWeakPower(world, x, y, z, side)
	}

	/**
	 * Allows for drops from an ICustomDrops tile entity
	 * @param metadata the metadata of the block
	 * @return whether this block with that metadata has tile drops
	 */
	override def hasTileEntityDrops(metadata: Int): Boolean = {
		true
	}

	override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase,
			itemStack: ItemStack): Unit = {
		super.onBlockPlacedBy(world, x, y, z, entity, itemStack)

		if (itemStack == null) return

		entity match {
			case player: EntityPlayer =>

				val tileEntity: TileEntity = world.getTileEntity(x, y, z)

				if (itemStack.hasTagCompound &&
						itemStack.getTagCompound.hasKey("preferredPlayers")) {
					tileEntity match {
						case emitter: TEEmitter =>
							emitter.readPlayersFromNBT(itemStack.getTagCompound)
						case _ =>
					}
				}
				else {
					tileEntity match {
						case emitter: TEOwnerEmitter =>
							emitter.setOwner(player)
						case emitter: TEPlayerEmitter =>

						case _ =>
					}
				}
			case _ =>

		}

	}

}
