package br.com.egp.envy.resource;

import br.com.egp.envy.service.ReportDataService;
import br.com.egp.envy.specification.TransactionSpec;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/reportData")
public class ReportDataResource {

    @Autowired
    private ReportDataService service;

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "fromDate and toDate",
                    value = "Get reports with data that is on this period",
                    required = false,
                    dataType = "date",
                    paramType = "query"
            )
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getReportData(
            @RequestParam(value="fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd")  Date fromDate,
            @RequestParam(value="toDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
        return ResponseEntity.ok().body(service.getReportData(fromDate, toDate));
    }
}
