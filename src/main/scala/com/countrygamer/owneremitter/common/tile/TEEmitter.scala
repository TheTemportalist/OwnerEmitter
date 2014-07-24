package com.countrygamer.owneremitter.common.tile

import java.util
import java.util.Random

import com.countrygamer.cgo.wrapper.common.tile.TEWrapper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.{NBTTagCompound, NBTTagList}
import net.minecraft.server.MinecraftServer

/**
 *
 *
 * @author CountryGamer
 */
class TEEmitter(name: String) extends TEWrapper(name) {

	val random: Random = new Random()
	val onlinePlayers: util.List[String] = new util.ArrayList[String]()

	val searchingPlayers: util.List[String] = new util.ArrayList[String]()

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

	def getPreferredPlayers(): util.List[String] = {
		return this.searchingPlayers
	}

	override def writeToNBT(tagCom: NBTTagCompound): Unit = {
		super.writeToNBT(tagCom)

		val preferredPlayersList: NBTTagList = new NBTTagList()
		for (i <- 0 until this.getPreferredPlayers().size()) {
			val playerTag: NBTTagCompound = new NBTTagCompound()
			playerTag.setString("username", this.getPreferredPlayers().get(i))
			preferredPlayersList.appendTag(playerTag)
		}
		tagCom.setTag("preferredPlayers", preferredPlayersList)

	}

	override def readFromNBT(tagCom: NBTTagCompound): Unit = {
		super.readFromNBT(tagCom)

		val preferredPlayersList: NBTTagList = tagCom.getTagList("preferredPlayers", 10)
		this.searchingPlayers.clear()
		for (i <- 0 until preferredPlayersList.tagCount()) {
			this.addPlayer(preferredPlayersList.getCompoundTagAt(i).getString("username"))
		}

	}

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

	def getRedstonePower: Int = {
		if (!this.getPreferredPlayers.isEmpty) {
			var numberOfOnlinePlayers: Int = 0
			for (i <- 0 until this.getPreferredPlayers.size()) {
				if (this.isPlayerOnline(this.getPreferredPlayers.get(i))) {
					numberOfOnlinePlayers += 1
				}
			}
			return (numberOfOnlinePlayers.asInstanceOf[Double] /
					this.getPreferredPlayers.size().asInstanceOf[Double] * 15.0D).asInstanceOf[Int]
		}
		0
	}

	def isPlayerOnline(username: String): Boolean = {
		this.onlinePlayers.contains(username)
	}

}
