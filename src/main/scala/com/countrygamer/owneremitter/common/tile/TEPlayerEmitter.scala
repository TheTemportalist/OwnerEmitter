package com.countrygamer.owneremitter.common.tile

import java.util
import java.util.Random

import com.countrygamer.cgo.wrapper.common.tile.TEWrapper
import com.countrygamer.owneremitter.common.block.OEBlocks
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.server.MinecraftServer

/**
 *
 *
 * @author CountryGamer
 */
class TEPlayerEmitter() extends TEWrapper(OEBlocks.ownerEmitter.getLocalizedName) {

	val random: Random = new Random()
	val onlinePlayers: util.List[String] = new util.ArrayList[String]()
	val searchingPlayers: util.List[String] = new util.ArrayList[String]()

	override def updateEntity(): Unit = {
		super.updateEntity()

		if (this.random.nextInt(20) == 0) {
			this.refreshPlayers()
		}

	}

	def refreshPlayers(): Unit = {
		val serverPlayers: util.List[_] = MinecraftServer.getServer.getConfigurationManager
				.playerEntityList

		this.onlinePlayers.clear()
		for (i <- 0 until serverPlayers.size()) {
			serverPlayers.get(i) match {
				case player: EntityPlayer =>
					onlinePlayers.add(player.getCommandSenderName)
				case _ =>
			}
		}

	}

	def addPlayer(player: EntityPlayer): Unit = {
		this.addPlayer(player.getCommandSenderName)
	}

	def addPlayer(username: String): Unit = {
		this.searchingPlayers.add(username)
	}

	def removePlayer(player: EntityPlayer): Unit = {
		this.removePlayer(player.getCommandSenderName)
	}

	def removePlayer(username: String): Unit = {
		this.searchingPlayers.remove(username)
	}

	def getRedstonePower: Int = {
		if (!this.searchingPlayers.isEmpty) {
			var numberOfOnlinePlayers: Int = 0
			for (i <- 0 until this.searchingPlayers.size()) {
				if (this.isPlayerOnline(this.searchingPlayers.get(i))) {
					numberOfOnlinePlayers += 1
				}
			}
			return (numberOfOnlinePlayers.asInstanceOf[Double] /
					this.searchingPlayers.size().asInstanceOf[Double] * 15.0D).asInstanceOf[Int]
		}
		0
	}

	def isPlayerOnline(username: String): Boolean = {
		this.onlinePlayers.contains(username)
	}

}
