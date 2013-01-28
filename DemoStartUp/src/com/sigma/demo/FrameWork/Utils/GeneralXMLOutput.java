package com.sigma.demo.FrameWork.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bouncycastle.asn1.tsp.TSTInfo;
import org.testng.log4testng.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sigma.demo.Constants.CommonUtils;
import com.sigma.demo.Constants.DataConstants;
import com.sigma.demo.FrameWork.Helpers.XMLDataHelper;

public class GeneralXMLOutput {

	static Logger log = Logger.getLogger(GeneralXMLOutput.class);

	private static final String TAGNAME_TESTSUITE = "testsuite";
	private static final String TAGNAME_TESTCASE = "testcase";
	private static final String TAGNAME_TESTID = "id";
	private static final String TAGNAME_TESTDESCRIPTION = "testdetails";
	private static final String TAGNAME_TESTSTATUS = "teststatus";
	private static final String TAGNAME_TESTERROR = "testerror";
	private static final String TAGNAME_TESTSEVERITY = "testseverity";
	private static final String TAGNAME_TESTSCREENSHOT ="testscreenshot";

	public static boolean updateXMLFile(
			ArrayList<XMLDataHelper> xmlDataDetails, String testId,
			String testCaseName, String testCaseStatus, String testCaseReason,
			String testSeverity, String testScreenShot) {
		XMLDataHelper xmlDataHelper = new XMLDataHelper();
		xmlDataHelper.setTestCaseID(CommonUtils.isNULL(testId));
		xmlDataHelper.setTestCaseName(CommonUtils.isNULL(testCaseName));
		xmlDataHelper.setTestCaseStatus(CommonUtils.isNULL(testCaseStatus));
		xmlDataHelper.setTestCaseError(CommonUtils.isNULL(testCaseReason));
		xmlDataHelper.setTestCaseSeverity(CommonUtils.isNULL(testSeverity));
		xmlDataHelper.setTestScreenShot(CommonUtils.isNULL(testScreenShot));
		xmlDataDetails.add(xmlDataHelper);
		return true;
	}

	public static boolean updateXMLFileContent(String fileDetails,
			ArrayList<XMLDataHelper> xmlData) {
		try {
		//	TestSheetDetails testSheetDetails = new TestSheetDetails();
			DocumentBuilderFactory documentBuilder = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = documentBuilder.newDocumentBuilder();

			Element root;
			Document doc;
			File fileDet = new File(fileDetails);
			BufferedWriter out = null;
			if (fileDet.exists()) {
				doc = docBuilder.parse(fileDet);
				root = doc.getDocumentElement();
				FileWriter fstream = new FileWriter(fileDetails);
				out = new BufferedWriter(fstream);

			} else {
				FileWriter fileData = new FileWriter(fileDetails);
				out = new BufferedWriter(fileData);
				doc = docBuilder.newDocument();
				root = doc.createElement(TAGNAME_TESTSUITE);
				root.setAttribute(TAGNAME_TESTID,
						DemoStartUp.testSheet.getTestSuiteName());
				root.setAttribute("name", DemoStartUp.testSheet.getTestSuiteName());
				doc.appendChild(root);
			}
			for (int i = 0; i < xmlData.size(); i++) {
				XMLDataHelper xmlDetails = xmlData.get(i);
				Element element = createNode(doc, xmlDetails);
				root.appendChild(element);
			}
			String xmlString = createStringXML(doc);
			out.write(xmlString);
			out.flush();
			out.close();
			return true;
		} catch (ParserConfigurationException parserExc) {
			log.error(parserExc.getMessage());
			return false;
		} catch (SAXException saxExc) {
			log.error(saxExc.getMessage());
			return false;
		} catch (IOException ioEx) {
			log.error(ioEx.getMessage());
			return false;
		}
	}

	public static Element createNode(Document doc, XMLDataHelper xmlDataHelper) {
		Element parent = doc.createElement(TAGNAME_TESTCASE);

		Element iNode = doc.createElement(TAGNAME_TESTID);
		parent.appendChild(iNode);
		iNode.appendChild(doc.createTextNode(xmlDataHelper.getTestCaseID()));

		Element Name = doc.createElement(TAGNAME_TESTDESCRIPTION);
		parent.appendChild(Name);
		Name.appendChild(doc.createTextNode(xmlDataHelper.getTestCaseName()));

		Element Status = doc.createElement(TAGNAME_TESTSTATUS);
		parent.appendChild(Status);
		Status.appendChild(doc.createTextNode(xmlDataHelper.getTestCaseStatus()));

		Element Error = doc.createElement(TAGNAME_TESTERROR);
		parent.appendChild(Error);
		Error.appendChild(doc.createTextNode(xmlDataHelper.getTestCaseError()));
        System.out.println(xmlDataHelper.getTestCaseError());
        
        Element screenShot = doc.createElement(TAGNAME_TESTSCREENSHOT);
        parent.appendChild(screenShot);
        screenShot.appendChild(doc.createTextNode(xmlDataHelper.getTestScreenShot()));
        
        
		Element Severity = doc.createElement(TAGNAME_TESTSEVERITY);
		parent.appendChild(Severity);
		Severity.appendChild(doc.createTextNode(xmlDataHelper.getTestCaseSeverity()));
     /*   System.out.println(xmlDataHelper.getTestCaseSeverity());*/
		
        NodeList nodeList = parent.getElementsByTagName("testcase");
        System.out.println(nodeList);
       // String valueTest = ((Node) nodeList.item(0)).getNodeName();
       // System.out.println(valueTest);
		return parent;

	}

	public static String createStringXML(Document doc) {
		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			StringWriter swt = new StringWriter();
			DOMSource domSource = new DOMSource(doc);
			StreamResult result = new StreamResult(swt);

			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			transformer
					.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(domSource, result);
			return swt.toString();
		} catch (TransformerConfigurationException transfConf) {
			log.error(transfConf.getMessage());
		} catch (TransformerException transfExc) {
			log.error(transfExc.getMessage());
		}
		return null;
	}
}
