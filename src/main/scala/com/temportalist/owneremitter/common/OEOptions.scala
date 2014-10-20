package com.temportalist.owneremitter.common

import com.temportalist.origin.library.common.register.OptionRegister
import net.minecraftforge.common.config.Configuration

/**
 *
 *
 * @author TheTemportalist
 */
object OEOptions extends OptionRegister {

	var enableOwnerEmitter: Boolean = true
	var enablePlayerEmitter: Boolean = false

	override def register(): Unit = {

		OEOptions.enableOwnerEmitter = this.getAndComment(
			Configuration.CATEGORY_GENERAL,
			"Enable Owner Emitter",
			"Enable the owner emitter block",
			value = true
		)

		OEOptions.enablePlayerEmitter = this.getAndComment(
			Configuration.CATEGORY_GENERAL,
			"Enable Player Emitter",
			"Enable the player emitter block. WARNING: UNFINISHED!",
			value = true
		)

	}

}
