package c2

import jcsp.lang.*

class ListToStream implements CSProcess{

	def ChannelInput inChannel
	def ChannelOutput outChannel
	def testList = []

	void run (){
		def inList = inChannel.read()
		while (inList[0] != -1) {
			// hint: output	list elements as single integers
			for(i in 0 ..< inList.size) {
				outChannel.write(inList[i])
				testList = testList << inList[i];
			}
			inList = inChannel.read()
		}
		outChannel.write(-1)
	}
}