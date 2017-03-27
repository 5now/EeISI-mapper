package it.infocert.eigor.model.core.converter.pa2core.mapping;

import it.infocert.eigor.model.core.datatypes.Identifier;
import it.infocert.eigor.model.core.model.BT63SellerTaxRepresentativeVatIdentifier;
import it.infocert.eigor.model.core.model.CoreInvoice;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class IdFiscaleIvaToBT63 {
    private final static String XPATHEXPRESSIONCOUNTRY = "//RappresentanteFiscale/DatiAnagrafici/IdFiscaleIVA/IdPaese";
    private final static String XPATHEXPRESSIONCODE = "//RappresentanteFiscale/DatiAnagrafici/IdFiscaleIVA/IdCodice";

    public static void convertTo(Document doc, CoreInvoice coreInvoice) {
        NodeList countryNodes = CommonConversionModule.evaluateXpath(doc, XPATHEXPRESSIONCOUNTRY);
        NodeList codeNodes = CommonConversionModule.evaluateXpath(doc, XPATHEXPRESSIONCODE);
        String countryCode = countryNodes.item(0).getTextContent();
        String vatCode = codeNodes.item(0).getTextContent();
        List<BT63SellerTaxRepresentativeVatIdentifier> identifiers = new ArrayList<>(1);
        identifiers.add(new BT63SellerTaxRepresentativeVatIdentifier(new Identifier(countryCode + vatCode)));
        coreInvoice.getBg11SellerTaxRepresentativeParties().get(0).setBt63SellerTaxRepresentativeVatIdentifiers(identifiers);
    }

}
