package com.countrygamer.owneremitter.client

import com.countrygamer.owneremitter.common.CommonProxy
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

/**
 *
 *
 * @author CountryGamer
 */
class ClientProxy() extends CommonProxy() {

	override def registerRender(): Unit = {

	}

	override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int,
			z: Int): AnyRef = {
		null
	}

}
