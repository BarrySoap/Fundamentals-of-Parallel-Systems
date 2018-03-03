package c09

import jcsp.lang.*
import groovyJCSP.*

class EventFix implements CSProcess {
	boolean passed = true
	def ChannelInput inChannel
	def ChannelOutput outChannel
	
	void run() {
		while (true) {
			def eventData = inChannel.read().copy()
			if (eventData.data != 100 && eventData.data != eventData.prev + 
				eventData.missed + 1) 
			{
				println "Missed"
			}
			outChannel.write(eventData)
		}
	}
}
