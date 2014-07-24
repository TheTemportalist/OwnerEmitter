package com.countrygamer.owneremitter.common.block

import com.countrygamer.cgo.wrapper.common.block.BlockWrapperTE
import com.countrygamer.owneremitter.common.tile.TEPlayerEmitter
import net.minecraft.block.material.Material
import net.minecraft.item.ItemBlock
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.IBlockAccess

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
		if (tileEntity != null && tileEntity.isInstanceOf[TEPlayerEmitter])
			return tileEntity.asInstanceOf[TEPlayerEmitter].getRedstonePower
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

}
