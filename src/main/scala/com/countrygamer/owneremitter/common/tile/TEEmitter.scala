package com.countrygamer.owneremitter.common.tile

import java.util
import java.util.Random

import com.countrygamer.cgo.common.lib.util.Player
import com.countrygamer.cgo.wrapper.common.tile.TEWrapper
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
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

	val preferredPlayers: util.List[String] = new util.ArrayList[String]()

	def addPlayer(player: EntityPlayer): Unit = {
		this.addPlayer(player.getCommandSenderName)
	}

	def addPlayer(username: String): Unit = {
		this.preferredPlayers.add(username)
	}

	def removePlayer(player: EntityPlayer): Boolean = {
		this.removePlayer(player.getCommandSenderName)
	}

	def removePlayer(username: String): Boolean = {
		if (this.preferredPlayers.contains(username)) {
			this.preferredPlayers.remove(username)
			return true
		}
		false
	}

	def getPreferredPlayers(): util.List[String] = {
		this.preferredPlayers
	}

	override def writeToNBT(tagCom: NBTTagCompound): Unit = {
		super.writeToNBT(tagCom)

		this.writePlayersToNBT(tagCom)

	}

	def writePlayersToNBT(tagCom: NBTTagCompound): Unit = {

		if (this.preferredPlayers.isEmpty)
			return

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

		this.readPlayersFromNBT(tagCom)

	}

	def readPlayersFromNBT(tagCom: NBTTagCompound): Unit = {

		val preferredPlayersList: NBTTagList = tagCom.getTagList("preferredPlayers", 10)
		this.preferredPlayers.clear()
		for (i <- 0 until preferredPlayersList.tagCount()) {
			this.addPlayer(preferredPlayersList.getCompoundTagAt(i).getString("username"))
		}

	}

	override def updateEntity(): Unit = {
		super.updateEntity()

		if (this.random.nextInt(20) == 0) {
			this.refreshPlayers()
			this.getWorldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord,
				this.getWorldObj.getBlock(this.xCoord, this.yCoord, this.zCoord))

		}

	}

	def refreshPlayers(): Unit = {

		if (MinecraftServer.getServer == null ||
				MinecraftServer.getServer.getConfigurationManager == null) {
			return
		}

		// Get players online
		var serverPlayers: Array[String] = Array[String]()
		if (MinecraftServer.getServer.getConfigurationManager != null) {
			serverPlayers = MinecraftServer.getServer.getConfigurationManager.getAllUsernames
			//System.out.println("Number of online players = " + serverPlayers.length)
		}

		// Save previous players online
		val previousOnlinePlayers: util.List[String] = this.onlinePlayers
		// Clear the new online players
		this.onlinePlayers.clear()
		// Store usernames of all players on the server to the onlinePlayers list
		for (i <- 0 until serverPlayers.length) {

			onlinePlayers.add(serverPlayers(i))

		}

	}

	def getRedstonePower: Int = {
		this.refreshPlayers()
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

	override def getDrops(drops: util.ArrayList[ItemStack], block: Block,
			metadata: Int): Unit = {
		drops.clear()

		val dropStack: ItemStack = new ItemStack(block, 1, metadata)
		if (!this.preferredPlayers.isEmpty) {
			val tagCom: NBTTagCompound = new NBTTagCompound()
			this.writePlayersToNBT(tagCom)
			dropStack.setTagCompound(tagCom)
		}
		drops.add(dropStack)

	}

	def tellPlayerOfList(player: EntityPlayer): Unit = {
		var playerListString: String = "    "
		if (this.preferredPlayers.isEmpty) {
			playerListString = playerListString + "None"
		}
		for (i <- 0 until this.preferredPlayers.size()) {
			if (i != 0) {
				playerListString = playerListString + ", "
			}
			playerListString = playerListString + this.preferredPlayers.get(i)

		}

		if (this.getWorldObj.isRemote) {
			Player.sendMessageToPlayer(player, "Players following:")
			Player.sendMessageToPlayer(player, playerListString)
		}

	}

}
