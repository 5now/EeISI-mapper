package it.infocert.eigor.model.core.converter.pa2core.mapping;

import it.infocert.eigor.model.core.datatypes.Identifier;
import it.infocert.eigor.model.core.model.BT31SellerVatIdentifier;
import it.infocert.eigor.model.core.model.CoreInvoice;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class IdFiscaleIvaToBT31 {
    private final static String XPATHEXPRESSIONCOUNTRY = "//CedentePrestatore/DatiAnagrafici/IdFiscaleIVA/IdPaese";
    private final static String XPATHEXPRESSIONCODE = "//CedentePrestatore/DatiAnagrafici/IdFiscaleIVA/IdCodice";

    public static void convertTo(Document doc, CoreInvoice coreInvoice) {
        NodeList countryNodes = CommonConversionModule.evaluateXpath(doc, XPATHEXPRESSIONCOUNTRY);
        NodeList codeNodes = CommonConversionModule.evaluateXpath(doc, XPATHEXPRESSIONCODE);
        String countryCode = countryNodes.item(0).getTextContent();
        String vatCode = codeNodes.item(0).getTextContent();
        List<BT31SellerVatIdentifier> identifiers = new ArrayList<>(1);
        identifiers.add(new BT31SellerVatIdentifier(new Identifier(countryCode + vatCode)));
        coreInvoice.getBg04Sellers().get(0).setBt31SellerVatIdentifiers(identifiers);
    }
}
