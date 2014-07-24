package com.countrygamer.owneremitter.common.item

import java.util

import com.countrygamer.owneremitter.common.block.OEBlocks
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemBlock, ItemStack}
import net.minecraft.nbt.NBTTagList

/**
 *
 *
 * @author CountryGamer
 */
class IBOwnerEmitter() extends ItemBlock(OEBlocks.ownerEmitter) {

	override def addInformation(itemStack: ItemStack, player: EntityPlayer,
			list: util.List[_], isAdvanced: Boolean): Unit = {

		if (itemStack != null &&
				itemStack.getItem == Item.getItemFromBlock(OEBlocks.ownerEmitter) &&
				itemStack.hasTagCompound && itemStack.getTagCompound.hasKey("preferredPlayers")) {

			val playerList: NBTTagList = itemStack.getTagCompound.getTagList("preferredPlayers", 10)

			for (i <- 0 until playerList.tagCount()) {
				list.add(playerList.getCompoundTagAt(i).getString("username"))
			}

		}

	}

}
