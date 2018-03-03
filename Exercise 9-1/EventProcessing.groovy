package c09;
   
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import jcsp.lang.*
import groovyJCSP.*
import groovyJCSP.plugAndPlay.*
import groovyJCSP.util.*

class EventProcessing implements CSProcess{ 
	 
  def ChannelInputList eventStreams
  def minTime = 500
  def maxTime = 750
  
  void run() {
    def mux2udd = Channel.one2one()
    def udd2prn = Channel.one2one()    
    def pList = [  
                   new FairMultiplex ( inChannels: eventStreams,
                                        outChannel: mux2udd.out() ),
/*                   
                   new PriMultiplex ( inChannels: eventStreams,
                                      outChannel: mux2udd.out() ),                     
*/                   

/*                   
                   new Multiplexer ( inChannels: eventStreams,
                                     outChannel: mux2udd.out() ),                     
*/
                   new UniformlyDistributedDelay ( inChannel:mux2udd.in(), 
                                                   outChannel: udd2prn.out(), 
                                                   minTime: minTime, 
                                                   maxTime: maxTime ), 
                   new GPrint ( inChannel: udd2prn.in(),
         		                 heading : "Event Output",
         		                 delay: 0)                    
                 ]
    new PAR (pList).run()
  }
}
