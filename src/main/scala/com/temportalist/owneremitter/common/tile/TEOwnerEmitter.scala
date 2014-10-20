package com.temportalist.owneremitter.common.tile

import com.temportalist.owneremitter.common.block.OEBlocks
import net.minecraft.entity.player.EntityPlayer

/**
 *
 *
 * @author TheTemportalist
 */
class TEOwnerEmitter() extends TEEmitter(OEBlocks.ownerEmitter.getLocalizedName) {

	def setOwner(player: EntityPlayer): Unit = {
		this.preferredPlayers.clear()
		this.addPlayer(player)
	}

	def hasOwner(): Boolean = {
		this.preferredPlayers.size() > 0
	}

	def getOwner(): String = {
		if (this.hasOwner()) {
			return this.preferredPlayers.get(0)
		}

		null
	}

}
