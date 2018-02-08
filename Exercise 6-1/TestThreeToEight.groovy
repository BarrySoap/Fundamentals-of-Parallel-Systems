package c2;

import jcsp.lang.*
import groovyJCSP.*
import groovy.util.GroovyTestCase;

class TestThreeToEight extends GroovyTestCase {
	void testMethod() {
		One2OneChannel connect1 = Channel.createOne2One()
		One2OneChannel connect2 = Channel.createOne2One()

		def genSetOfThree = new GenerateSetsOfThree ( outChannel: connect1.out() )
		def listStream = new ListToStream ( inChannel: connect1.in(), outChannel: connect2.out() )
		def createSetOfEight = new CreateSetsOfEight ( inChannel: connect2.in() )
		
		def testList = [genSetOfThree, listStream, createSetOfEight]
		
		new PAR (testList).run()
		def expected = listStream.testList
		def actual = createSetOfEight.testList
		
		for (i in 0..22) {
			assertTrue(expected == actual)
			assertFalse(expected != actual)
		}
	}
}
