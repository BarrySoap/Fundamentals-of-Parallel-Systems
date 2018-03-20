package c11

import groovyJCSP.*
import jcsp.lang.*
import phw.util.*
import jcsp.awt.*
import java.awt.*

class UserInterface implements CSProcess {	
	
	def ActiveCanvas scalerCanvas
	def int canvasSize
	def ChannelInput scaleValueConfig
	def ChannelInput suspendButtonConfig
	def ChannelInput printValueConfig
	def ChannelOutput buttonEvent
	
	void run() {
		def root = new ActiveClosingFrame ("SCALER")
		def mainFrame = root.getActiveFrame()
		def printText = new ActiveTextArea(printValueConfig, null)
		def suspendButton = new ActiveButton(suspendButtonConfig, buttonEvent, "SUSPEND")
		def scaleLabel = new Label ("Current Scale")
		def scale = new ActiveLabel (scaleValueConfig)
		
		def newScaleLabel = new Label("Enter New Scale")
		def newScale = new ActiveTextEnterField(null, buttonEvent)
		Panel newScalePanel = new Panel (new GridLayout (1, 2));
		newScalePanel.add (newScale.getActiveTextField ());
		
		def scalerContainer = new Container()
		scalerContainer.setLayout(new GridLayout (1, 1))
		scalerContainer.add(suspendButton)
		scalerContainer.add(scaleLabel)
		scalerContainer.add(scale)
		scalerContainer.add(newScaleLabel)
		scalerContainer.add(newScalePanel)
		scalerContainer.add(printText)
		scalerCanvas.setSize(canvasSize, canvasSize)
		mainFrame.setLayout(new BorderLayout())
		mainFrame.add(scalerCanvas, BorderLayout.CENTER)
		mainFrame.add(scalerContainer, BorderLayout.SOUTH)
		mainFrame.pack()
		mainFrame.setVisible(true)
		
		def network = [ root, scalerCanvas, printText, scale, newScale, suspendButton]		
		new PAR (network).run()
		
	}
}