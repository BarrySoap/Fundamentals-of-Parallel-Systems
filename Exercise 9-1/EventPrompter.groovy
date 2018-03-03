package c09

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import jcsp.lang.*
import groovyJCSP.*

class EventPrompter implements CSProcess {  
	
  def ChannelInput inChannel
  def ChannelOutput getChannel
  def ChannelOutput outChannel  
  
  void run () {
    def s = 1
    while (true) {
      getChannel.write(s)
      def e = inChannel.read().copy()
      outChannel.write( e )
    }
  }
}

