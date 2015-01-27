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
 * Created by nicho on 26/01/15.
 */
@Path("tagsoup")
public class TagSoupResource {
    @POST
    @Produces("text/html")
    public String getHtml(@FormParam("input") String input) throws ParsingException, IOException, SAXException, ParserConfigurationException, TransformerException {
        Parser p = new Parser();
        p.setFeature(Parser.namespacePrefixesFeature, false);
        p.setFeature(Parser.namespacesFeature, false);
        p.setFeature(Parser.xmlnsURIsFeature, false);
        SAX2DOM sax2dom = new SAX2DOM();
        p.setContentHandler(sax2dom);
        p.parse(new InputSource( new StringReader(input)));
        Node doc = sax2dom.getDOM();
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String html = writer.toString().substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());

        Document document = Jsoup.parse(html);
        Document.OutputSettings out = new Document.OutputSettings();
        out.prettyPrint(true);
        out.escapeMode(Entities.EscapeMode.xhtml);
        return document.html();
    }
}
