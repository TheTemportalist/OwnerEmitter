package com.temportalist.owneremitter.common.item

import java.util

import com.temportalist.origin.library.client.utility.Keys
import com.temportalist.owneremitter.common.block.OEBlocks
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemBlock, ItemStack}
import net.minecraft.nbt.{NBTTagCompound, NBTTagList}
import net.minecraft.util.EnumChatFormatting

/**
 *
 *
 * @author TheTemportalist
 */
class IBEmitter(block: Block) extends ItemBlock(block) {

	override def addInformation(itemStack: ItemStack, player: EntityPlayer,
			list: util.List[_], isAdvanced: Boolean): Unit = {

		list.asInstanceOf[util.List[String]].add("")

		if (!Keys.keyPressed_Shift_Gui()) {
			list.asInstanceOf[util.List[String]]
					.add(EnumChatFormatting.BLUE + "Hold SHIFT for players")
		}
		else {
			list.asInstanceOf[util.List[String]].add("Players Following: ")

			if (itemStack != null &&
					itemStack.getItem == Item.getItemFromBlock(OEBlocks.ownerEmitter) &&
					itemStack.hasTagCompound &&
					itemStack.getTagCompound.hasKey("preferredPlayers")) {

				val playerList: NBTTagList = itemStack.getTagCompound
						.getTagList("preferredPlayers", 10)

				for (i <- 0 until playerList.tagCount()) {
					val playerTag: NBTTagCompound = playerList.getCompoundTagAt(i)
					if (playerTag != null)
						list.asInstanceOf[util.List[String]]
								.add("  " + playerTag.getString("username"))
				}

			}
		}

	}

}
