package com.temportalist.owneremitter.common.block

import com.temportalist.origin.wrapper.common.block.BlockWrapperTE
import com.temportalist.owneremitter.common.item.IBEmitter
import com.temportalist.owneremitter.common.tile.{TEEmitter, TEOwnerEmitter, TEPlayerEmitter}
import net.minecraft.block.material.Material
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.{IBlockAccess, World}

/**
 *
 *
 * @author TheTemportalist
 */
class BlockEmitter(pluginID: String, name: String, tileEntityClass: Class[_ <: TileEntity])
		extends BlockWrapperTE(Material.rock, pluginID, name, classOf[IBEmitter], tileEntityClass) {

	// Default Constructor
	{

	}

	// End Constructor

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

		// Make sure itemstack was valid
		if (itemStack == null) return

		// Check entity instances
		entity match {
			// if entity is player
			case player: EntityPlayer =>

				// Get tile entity
				val tileEntity: TileEntity = world.getTileEntity(x, y, z)

				// Check for player list
				if (itemStack.hasTagCompound &&
						itemStack.getTagCompound.hasKey("preferredPlayers")) {
					// check te instances
					tileEntity match {
						// if te is emitter
						case emitter: TEEmitter =>
							// set emitter player list from itemstack player list
							emitter.readPlayersFromNBT(itemStack.getTagCompound)

						// default
						case _ =>

					}
				}

			// default
			case _ =>

		}

		// Refresh blocks around for emitter power
		world.notifyBlocksOfNeighborChange(x, y, z, this)

	}

	override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer,
			side: Int, offsetX: Float, offsetY: Float, offsetZ: Float): Boolean = {
		val tileEntity: TileEntity = world.getTileEntity(x, y, z)
		tileEntity match {
			case emitter: TEEmitter =>
				if (player.isSneaking) {

					emitter match {
						case owner: TEOwnerEmitter =>
							if (owner.getPreferredPlayers().size() >= 1) {
								owner.removePlayer(player)
							}
							else {
								owner.setOwner(player)
							}

						case teplayer: TEPlayerEmitter =>
							if (!teplayer.removePlayer(player)) {
								teplayer.addPlayer(player)
							}

						case _ =>

					}

					world.notifyBlocksOfNeighborChange(x, y, z, this)
				}
				else {
					emitter.tellPlayerOfList(player)
				}
				return true

			case _ =>

		}

		false
	}

}
