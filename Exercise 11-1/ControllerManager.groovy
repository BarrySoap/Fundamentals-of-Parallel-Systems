package c11

import groovyJCSP.*
import jcsp.lang.*
import phw.util.*
import jcsp.awt.*
import java.awt.*

class ControllerManager implements CSProcess {
	
	def ChannelInput print
	
	def ChannelOutput toUIPrint
	
	def ChannelInput fromScale
	def ChannelOutput toScaleSuspend
	def ChannelOutput toScaleInject
	def int CANVASSIZE
	def int START_SCALE
	def ChannelInput fromUIButtons
	def ChannelOutput toUILabel
	def ChannelOutput toUISuspend
	
	void run() {
		// Send initial scale value to UI
		def scale = START_SCALE
		toUILabel.write( scale.toString() )
		
		
		while (true) {
				// Read direction from UI
				def direction = fromUIButtons.read()
				// If SUSPEND button pushed...
				if (direction == "SUSPEND") {
					// Change button to SUSPENDED (does nothing when clicked)
					toUISuspend.write("SUSPENDED")
					// Suspend Scale process
					toScaleSuspend.write(0)
					// Get current scale value and send to UI
					scale = fromScale.read()
					toUILabel.write(scale.toString())
					// Wait till direction is an integer value
					def integerScale = false
					while (!integerScale) {
						direction = fromUIButtons.read()
						try {
							// Get new scale value from direction
							direction = Integer.parseInt(direction);
							scale = direction
							integerScale = true
						} catch (NumberFormatException e) {
							System.out.println("Incorrect Value!");
						}
					}
					// Change button to SUSPEND and update scale in UI and Scale
					toUISuspend.write("SUSPEND")
					toUILabel.write(scale.toString())
					toScaleInject.write(scale)
				}
			
		}
	}	
}