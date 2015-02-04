package no.computas.workshop.mavenintro;

import nu.xom.ParsingException;
import org.apache.xalan.xsltc.trax.SAX2DOM;
import org.ccil.cowan.tagsoup.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Parser HTML-markup og returnerer et helt dokument.
 */
@Path("htmlparser")
public class HtmlParserResource {
    @POST
    @Produces("text/html")
    public String getHtml(@FormParam("input") String input) throws ParsingException, IOException, SAXException, ParserConfigurationException, TransformerException {
        //Tagsoup parser
        Parser parser = new Parser();
        parser.setFeature(Parser.namespacePrefixesFeature, false);
        parser.setFeature(Parser.namespacesFeature, false);
        parser.setFeature(Parser.xmlnsURIsFeature, false);

        //Sax2dom gir oss Document Object Model (DOM) av HTML parset av Tagsoup
        SAX2DOM sax2dom = new SAX2DOM();
        parser.setContentHandler(sax2dom);
        parser.parse(new InputSource(new StringReader(input)));
        Node doc = sax2dom.getDOM();

        //Gjør om DOM til en streng
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String html = writer.toString().substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());

        //Parser med Jsoup for å gjøre om til et XHTML-dokument som er fint formatert
        Document document = Jsoup.parse(html);
        Document.OutputSettings out = new Document.OutputSettings();
        out.prettyPrint(true);
        out.escapeMode(Entities.EscapeMode.xhtml);
        return document.html();
    }
}
