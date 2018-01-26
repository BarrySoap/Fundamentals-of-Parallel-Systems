package c4


import jcsp.lang.*
import groovyJCSP.*

class ResetSuccessor implements CSProcess {
	  
  def ChannelOutput outChannel
  def ChannelInput  inChannel
  def ChannelInput  resetChannel
	  
  void run () {
    def guards = [ resetChannel, inChannel  ]
    def alt = new ALT ( guards )
	while (true) {
	  // deal with inputs from resetChannel and inChannel
	  // use a priSelect
		def i = alt.priSelect()
		if (i.value == 0) {
			def j = resetChannel.read()
			inChannel.read()
			outChannel.write(j + 1)
		} else {
			def k = inChannel.read()
			outChannel.write(k + 1)
		}
	}
  }
}
