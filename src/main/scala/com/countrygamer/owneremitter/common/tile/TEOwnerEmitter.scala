package com.countrygamer.owneremitter.common.tile

import com.countrygamer.owneremitter.common.block.OEBlocks
import net.minecraft.entity.player.EntityPlayer

/**
 *
 *
 * @author CountryGamer
 */
class TEOwnerEmitter() extends TEEmitter(OEBlocks.ownerEmitter.getLocalizedName) {

	def setOwner(player: EntityPlayer): Unit = {
		this.preferredPlayers.clear()
		this.addPlayer(player)
	}

}
