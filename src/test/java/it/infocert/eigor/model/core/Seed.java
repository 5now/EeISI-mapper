package it.infocert.eigor.model.core;

import it.infocert.eigor.model.core.enums.Iso4217CurrenciesFundsCodes;
import it.infocert.eigor.model.core.model.BT0001InvoiceNumber;
import it.infocert.eigor.model.core.model.BT0006VatAccountingCurrencyCode;

import it.infocert.eigor.model.core.model.CoreInvoice;
import it.infocert.eigor.model.core.rules.Br002AnInvoiceShallHaveAnInvoiceNumberRule;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Seed {

    @Test
    public void shouldApplyRule() {

        // given
        Br002AnInvoiceShallHaveAnInvoiceNumberRule rule = new Br002AnInvoiceShallHaveAnInvoiceNumberRule();

        CoreInvoice invoiceWithCoreInvoiceNumber = new CoreInvoice();
        invoiceWithCoreInvoiceNumber.getBt0001InvoiceNumbers().add(new BT0001InvoiceNumber( "1234" ));

        CoreInvoice invoiceWithoutCoreInvoiceNumber = new CoreInvoice();

        // when
        boolean outcome1 = rule.issCompliant(invoiceWithCoreInvoiceNumber);
        boolean outcome2 = rule.issCompliant(invoiceWithoutCoreInvoiceNumber);

        // then
        assertThat( outcome1, is(true) );
        assertThat( outcome2, is(false) );

    }



    @Test
    public void justStart() {

        // given
        CoreInvoice coreInvoice = new CoreInvoice();
        BT0001InvoiceNumber invoiceNumber = new BT0001InvoiceNumber( "1234" );

        // when
        coreInvoice.getBt0001InvoiceNumbers().add(invoiceNumber);
        coreInvoice.getBt0006VatAccountingCurrencyCodes().add( new BT0006VatAccountingCurrencyCode(Iso4217CurrenciesFundsCodes.EUR) );

        // then
        assertThat( coreInvoice.getBt0001InvoiceNumbers().get(0), is(invoiceNumber) );

    }

}
