package ua.kiev.sinenko.mbankingmonitor.model.service;

import ua.kiev.sinenko.mbankingmonitor.model.entity.MbankingSms;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by a.sinenko on 03.02.2016.
 */
@LocalBean
@Stateless
public class MbankingSmsServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext(unitName = "infra")
    private EntityManager entityManager;

    public List<MbankingSms> findById(BigDecimal idsms) throws PersistenceException {
        List<MbankingSms> mbankingsms = (entityManager.createNamedQuery(MbankingSms.findByIdSms, MbankingSms.class).setParameter("idsms", idsms)).getResultList();
        if (mbankingsms.size() > 0) {
            return mbankingsms;
        }
        return null;
    }

    public List<MbankingSms> findLinkedRecords(BigDecimal idsms) throws PersistenceException {
        List<MbankingSms> mbankingsms = (entityManager.createNamedQuery(MbankingSms.findLinkedRecods, MbankingSms.class).setParameter("idsms", idsms)).getResultList();
        if (mbankingsms.size() > 0) {
            return mbankingsms;
        }
        return null;
    }

    public List<MbankingSms> findAll() throws PersistenceException {
        List<MbankingSms> mbankingsms = (entityManager.createNamedQuery(MbankingSms.findAll, MbankingSms.class)).getResultList();
        if (mbankingsms.size() > 0)
            return mbankingsms;
        return null;
    }

    public Long getCountCriteria(
            Date dateFrom, Date dateTo,
            boolean newSmsState,
            boolean workerDoneState,
            boolean receiverDoneState,
            boolean inWorkingState,
            boolean notWorkedState,
            boolean notParsedWrongCommand,
            boolean notWorkedNoDataState,
            boolean errorResponseRegState,
            String order )
    {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root mbankingsmsRoot = criteriaQuery.from(MbankingSms.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        Predicate predicateDate = criteriaBuilder.between(mbankingsmsRoot.get("smsDate"), dateFrom, dateTo);

        if(newSmsState){
            Predicate predicate0 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), 0);
            predicates.add(predicate0);
        }

        if(workerDoneState){
            Predicate predicate1 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), 1);
            predicates.add(predicate1);
        }

        if(receiverDoneState){
            Predicate predicate2 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), 2);
            predicates.add(predicate2);
        }

        if(inWorkingState){
            Predicate predicate3 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), 3);
            predicates.add(predicate3);
        }

        if(notWorkedState){
            Predicate predicate4 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), -1);
            predicates.add(predicate4);
        }

        if(notParsedWrongCommand) {
            Predicate predicate5 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), -2);
            predicates.add(predicate5);
        }

        if(notWorkedNoDataState) {
            Predicate predicate6 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), -3);
            predicates.add(predicate6);
        }

        if (errorResponseRegState) {
            Predicate predicate7 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), -4);
            predicates.add(predicate7);
        }

        //predicateStatuses = predicate0, predicate1, predicate2, predicate3, predicate4, predicate5, predicate6, predicate7
        Predicate predicateStatuses = criteriaBuilder.or(predicates.toArray(new Predicate[0]));

        if(predicateStatuses != null) {
            criteriaQuery.where(criteriaBuilder.and(predicateDate, predicateStatuses));
        }
        else {
            criteriaQuery.where(predicateDate);
        }

        criteriaQuery.select(criteriaBuilder.count(mbankingsmsRoot.get("id_sms")));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
        logger.info("=====================================================================Number of rows count: " + query.getSingleResult());

        return query.getSingleResult();
    }

    public List<MbankingSms> getBetweenDatesList(
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
            String order ){
        /*создает запрос типа
        "SELECT *
        FROM TTransfer
        WHERE to_date(transferstartdate,'DD.MM.YYYY') between to_date('01-02-16','DD.MM.YYYY')
            AND to_date('04-02-16','DD.MM.YYYY')
            AND (TRANSFERSTATUS=0 OR TRANSFERSTATUS=1 OR TRANSFERSTATUS=2 OR TRANSFERSTATUS=3
            OR TRANSFERSTATUS=4 OR TRANSFERSTATUS=5 OR TRANSFERSTATUS=6 OR TRANSFERSTATUS=7 OR TRANSFERSTATUS=8 )
            ORDER BY transferstartdate DESC;"
        */
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MbankingSms> criteriaQuery = criteriaBuilder.createQuery(MbankingSms.class);
        Root mbankingsmsRoot = criteriaQuery.from(MbankingSms.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        Predicate predicateDate = criteriaBuilder.between(mbankingsmsRoot.get("smsDate"), dateFrom, dateTo);

        if(newSmsState){
            Predicate predicate0 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), 0);
            predicates.add(predicate0);
        }

        if(workerDoneState){
            Predicate predicate1 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), 1);
            predicates.add(predicate1);
        }

        if(receiverDoneState){
            Predicate predicate2 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), 2);
            predicates.add(predicate2);
        }

        if(inWorkingState){
            Predicate predicate3 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), 3);
            predicates.add(predicate3);
        }

        if(notWorkedState){
            Predicate predicate4 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), -1);
            predicates.add(predicate4);
        }

        if(notParsedWrongCommand) {
            Predicate predicate5 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), -2);
            predicates.add(predicate5);
        }

        if(notWorkedNoDataState) {
            Predicate predicate6 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), -3);
            predicates.add(predicate6);
        }

        if (errorResponseRegState) {
            Predicate predicate7 = criteriaBuilder.equal(mbankingsmsRoot.get("state"), -4);
            predicates.add(predicate7);
        }

        //predicateStatuses = predicate0, predicate1, predicate2, predicate3, predicate4, predicate5, predicate6, predicate7
        Predicate predicateStatuses = criteriaBuilder.or(predicates.toArray(new Predicate[0]));

        if(predicateStatuses != null)
            criteriaQuery.where(criteriaBuilder.and(predicateDate, predicateStatuses));
        else
            criteriaQuery.where(predicateDate);


        if(order.equalsIgnoreCase("desc"))
            criteriaQuery.orderBy(criteriaBuilder.desc(mbankingsmsRoot.get("id_sms")));
        else
            criteriaQuery.orderBy(criteriaBuilder.asc(mbankingsmsRoot.get("id_sms")));


        TypedQuery<MbankingSms> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);

        List<MbankingSms> mbankingsms = null;
        mbankingsms = query.getResultList();
        logger.info("mbankingsms.size():_______________________________________{}", mbankingsms.size());
        if (mbankingsms.size() > 0) {
            return mbankingsms;
        }else
            return null;

    }


/*
    public BigDecimal findMaxTrId() throws PersistenceException {
        BigDecimal transfer = (entityManager.createNamedQuery(Transfer.findMax, Transfer.class)).getSingleResult().getTransferid();
        return transfer;
    }

    public List<MbankingSms> findTransferlastWeek() throws PersistenceException {
        List<MbankingSms> transfer = (entityManager.createNamedQuery(MbankingSms.findLastWeek, MbankingSms.class)).getResultList();
        if (transfer.size() > 0) {
            return transfer;
        }
        return null;
    }
*/




}
