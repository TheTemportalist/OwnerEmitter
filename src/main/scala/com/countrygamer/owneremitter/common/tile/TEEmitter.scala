package com.countrygamer.owneremitter.common.tile

import com.countrygamer.cgo.wrapper.common.tile.TEWrapper
import com.countrygamer.owneremitter.common.block.OEBlocks

/**
 *
 *
 * @author CountryGamer
 */
class TEEmitter() extends TEWrapper(OEBlocks.ownerEmitter.getLocalizedName) {

	def getRedstonePower: Int = {
		0
	}

}
