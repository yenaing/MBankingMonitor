package ua.kiev.sinenko.mbankingmonitor.model.service;

import ua.kiev.sinenko.mbankingmonitor.model.entity.MbankingSms;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by a.sinenko on 12.04.2016.
 */
public interface MBankingSmsService {
    List<MbankingSms> findById(BigDecimal idsms);

    List<MbankingSms> findLinkedRecords(BigDecimal idsms);

    List<MbankingSms> findAll();

    Long getCountCriteria(
            Date dateFrom, Date dateTo,
            boolean newSmsState,
            boolean workerDoneState,
            boolean receiverDoneState,
            boolean inWorkingState,
            boolean notWorkedState,
            boolean notParsedWrongCommand,
            boolean notWorkedNoDataState,
            boolean errorResponseRegState,
            String order );

    List<MbankingSms> getBetweenDatesList(
            int pageNumber,
            int pageSize,
            Date dateFrom, Date dateTo,
            boolean newSmsState,
            boolean workerDoneState,
            boolean receiverDoneState,
            boolean inWorkingState,
            boolean notWorkedState,
            boolean notParsedWrongCommand,
            boolean notWorkedNoDataState,
            boolean errorResponseRegState,
            String order );
}
