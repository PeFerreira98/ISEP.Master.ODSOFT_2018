package pt.isep.cms.contacts;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.contacts.client.ExampleGWTTest;
import pt.isep.cms.contacts.client.ContactsServiceAsyncGWTTest;

public class ExampleGWTTestSuit extends GWTTestSuite
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Test for the Contacts Application");
		suite.addTestSuite(ExampleGWTTest.class); 
		suite.addTestSuite(ContactsServiceAsyncGWTTest.class);
		return suite;
	}
} 