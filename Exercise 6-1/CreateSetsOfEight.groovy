package c2

import jcsp.lang.*

class CreateSetsOfEight implements CSProcess{

	def ChannelInput inChannel
	def testList = []

	void run(){
		def outList = []
		def v = inChannel.read()
		while (v != -1){
			for ( i in 0 .. 7 ) {
				// put v into outList and read next input
				outList.add(v);
				testList = testList << v
				v = inChannel.read();
			}
			println " Eight Object is ${outList}"
			outList.clear();
		}
		println "Finished"
	}
}