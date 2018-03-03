package c09
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import jcsp.lang.*
import groovyJCSP.*

class EventData implements Serializable, JCSPCopy {  
	
  def int source = 0
  def int data = 0
  def int missed = -1 
  def int prev
   
  def copy() {
    def e = new EventData ( source: this.source, 
                            data: this.data, 
                            missed: this.missed,
							prev: this.prev )
    return e
  }  
  
  def String toString() {
    def s = "EventData -> [source: "
    s = s + source + ", data: "
    s = s + data + ", missed: " 
    s = s + missed + "]"
    return s
  }   
    
}


