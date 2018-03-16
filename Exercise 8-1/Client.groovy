package c07

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import jcsp.lang.*
import groovyJCSP.*


class Client implements CSProcess {  
	
  def ChannelInput receiveChannel
  def ChannelOutput requestChannel
  def clientNumber
  def selectList = [ ]
  def propertyOrder = false
   
  void run () {
    def iterations = selectList.size
    println "Client $clientNumber has $iterations values in $selectList"
	
    for ( i in 0 ..< iterations) {
      def key = selectList[i]
      requestChannel.write(key)
      def v = receiveChannel.read()
	  println "Key: " + key
	  println "V: " + v
	  if (v == key * 10) {
		  propertyOrder = true;
	  }
    }
	
    println "Client $clientNumber has finished"
	
	if (propertyOrder == true) {
		println "Client $clientNumber in order"
	}
	else {
		println "Client $clientNumber not in order"
	}
  }
}
