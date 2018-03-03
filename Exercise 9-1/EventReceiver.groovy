package c09;

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import jcsp.lang.*
import groovyJCSP.*


class EventReceiver implements CSProcess { 
	 
  def ChannelInput eventIn
  def ChannelOutput eventOut
  
  void run() {
    while (true){
      eventOut.write(eventIn.read())
    }    
  }
}
