package xmlparser;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser {
	public static Vector<String> getXMLTagValue(String xmlFileString,
			String tagName) {
		Vector<String> tagVec = new Vector<>();
		// Use reluctant quantifier
		String pattern = "<" + tagName + ">(.+?)</" + tagName + ">";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(xmlFileString);
		while (m.find()) {
			tagVec.add(m.group(1));
		}
		return tagVec;
	}

	public static void main(String args[]) {
		// String to be scanned to find the pattern.
		String xmlFile = "<STUDENTS> <STUDENT> <NAME>HillyBilly</NAME> <AGE>19</AGE> <CLASS>10</CLASS>"
				+ "</STUDENT> <STUDENT> <NAME>Captain Kirk</NAME> <AGE>20</AGE> <CLASS>10</CLASS> "
				+ "</STUDENT> </STUDENTS>";
		Vector<String> v = getXMLTagValue(xmlFile, "STUDENT");
		Vector<String> ageV = getXMLTagValue(v.elementAt(0), "AGE");
		System.out.println("Age is : " + ageV.elementAt(0));
	}
}
