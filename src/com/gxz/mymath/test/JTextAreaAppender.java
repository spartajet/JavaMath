package com.gxz.mymath.test;

import javax.swing.JTextArea;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class JTextAreaAppender extends AppenderSkeleton {

	private JTextArea jTextArea = null;

	public JTextAreaAppender(Layout layout, javax.swing.JTextArea jText) {
		this.layout = layout;
		this.jTextArea = jText;
	}

	public JTextAreaAppender(JTextArea jText) {
		this.layout = new org.apache.log4j.PatternLayout("%p [%t] %c  - %m%n");
		this.jTextArea = jText;
	}

	public void setjTextArea(JTextArea jTextArea) {
		this.jTextArea = jTextArea;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		 if (this.closed)
		    {
		      return;
		    }
		    this.closed = true;
	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void append(LoggingEvent arg0) {
		// TODO Auto-generated method stub
		if(this.jTextArea == null)
		      return ;
		    this.subAppend(arg0);
	}
	
	public void subAppend(LoggingEvent event)
	  {
	      synchronized(jTextArea)
	      {
	        this.jTextArea.append(this.layout.format(event));
	      }

	  }


}
