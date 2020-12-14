package br.com.egp.envy.service;

import br.com.egp.envy.model.Envelope;
import br.com.egp.envy.model.ReportData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportDataService {

    @Autowired
    private EnvelopeService envelopeService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    public ReportData getReportData(Date fromDate, Date toDate) {
        List<Envelope> envelopeList = envelopeService.findEnvelopesByUser(userDetailsService.getLoggedUser());

        return null;
    }
}
