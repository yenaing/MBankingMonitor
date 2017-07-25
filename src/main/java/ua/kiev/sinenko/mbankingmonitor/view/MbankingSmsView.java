package ua.kiev.sinenko.mbankingmonitor.view;

import ua.kiev.sinenko.mbankingmonitor.model.entity.MbankingSms;
import ua.kiev.sinenko.mbankingmonitor.model.service.MbankingSmsServiceImpl;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

/**
 * Created by a.sinenko on 03.02.2016.
 */
@ManagedBean(name="mbankingsmsview")
@SessionScoped
public class MbankingSmsView implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EJB
    private MbankingSmsServiceImpl mbankingSmsServiceImpl;

    private List<MbankingSms> mbankingSmsList;

    private List<MbankingSms> linkedmbankingSmsList;

    private MbankingSms concreeteMbankingSms;

    //статусы операции перевода.
    private boolean newSmsState = true; //0	Смс не обрабатывалось
    private boolean workerDoneState = true; //1	SMS, сформированное Worker_ом, обработано
    private boolean receiverDoneState = true; //2	SMS, сформированное Receiver_ом, обработано
    private boolean inWorkingState = true; //3	SMS, в состоянии обработки
    private boolean notWorkedState = true; //-1	SMS не разобрано: нет данных для обработки
    private boolean notParsedWrongCommand = true; // -2	SMS не разобрано: некорректная команда
    private boolean notWorkedNoDataState = true; // -3	SMS не обработано: нет данных по результату обработки
    private boolean errorResponseRegState = true; // - 4	SMS не обработано: ошибка регистрации ответа

    //тип сортировки asc/desc
    private String selectOrder;

    private Date datenow;
    private Date dateyesterday;

    /*для постраничной выборки данных*/
    int pageNumber = 1;
    int pageSize = 20;
    long countRecords = 0;
    int maxPageNumber=1;

    public boolean isNewSmsState() {        return newSmsState;    }
    public void setNewSmsState(boolean newSmsState) {        this.newSmsState = newSmsState;    }

    public boolean isWorkerDoneState() {        return workerDoneState;    }
    public void setWorkerDoneState(boolean workerDoneState) {        this.workerDoneState = workerDoneState;    }

    public boolean isReceiverDoneState() {        return receiverDoneState;    }
    public void setReceiverDoneState(boolean receiverDoneState) {        this.receiverDoneState = receiverDoneState;    }

    public boolean isInWorkingState() {        return inWorkingState;    }
    public void setInWorkingState(boolean inWorkingState) {        this.inWorkingState = inWorkingState;    }

    public boolean isNotWorkedState() {        return notWorkedState;    }
    public void setNotWorkedState(boolean notWorkedState) {        this.notWorkedState = notWorkedState;    }

    public boolean isNotParsedWrongCommand() {        return notParsedWrongCommand;    }
    public void setNotParsedWrongCommand(boolean notParsedWrongCommand) {        this.notParsedWrongCommand = notParsedWrongCommand;    }

    public boolean isNotWorkedNoDataState() {        return notWorkedNoDataState;    }
    public void setNotWorkedNoDataState(boolean notWorkedNoDataState) {        this.notWorkedNoDataState = notWorkedNoDataState;    }

    public boolean isErrorResponseRegState() {        return errorResponseRegState;    }
    public void setErrorResponseRegState(boolean errorResponseRegState) {        this.errorResponseRegState = errorResponseRegState;    }

    public MbankingSms getConcreeteMbankingSms() {        return concreeteMbankingSms;    }
    public void setConcreeteMbankingSms(MbankingSms concreeteMbankingSms) {        this.concreeteMbankingSms = concreeteMbankingSms;    }

    public List<MbankingSms> getLinkedmbankingSmsList() {        return linkedmbankingSmsList;    }
    public void setLinkedmbankingSmsList(List<MbankingSms> linkedmbankingSmsList) {        this.linkedmbankingSmsList = linkedmbankingSmsList;    }

    private void setDefault(){
        int pageNumber = 1;
        int pageSize = 20;
        newSmsState = true;
        workerDoneState = true;
        receiverDoneState = true;
        inWorkingState = true;
        notWorkedState = true;
        notParsedWrongCommand = true;
        notWorkedNoDataState = true;
        errorResponseRegState = true;
        datenow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datenow);
        calendar.add(Calendar.DATE, -7);
        dateyesterday = calendar.getTime();
        selectOrder = "desc";
        mbankingSmsList = null;
    }

    @PostConstruct
    public void init() {
        setDefault();
        getBetweenDatesList();
        }


    public String getSelectOrder() {  return selectOrder; }
    public void setSelectOrder(String selectOrder) {  this.selectOrder = selectOrder; }

    public List<MbankingSms> getMbankingSmsList() {        return mbankingSmsList;    }
    public void setMbankingSmsList(List<MbankingSms> mbankingSmsList) {        this.mbankingSmsList = mbankingSmsList;    }

    public Date getDatenow() { return datenow; }
    public void setDatenow(Date datenow) { this.datenow = datenow; }

    public Date getDateyesterday() { return dateyesterday; }
    public void setDateyesterday(Date dateyesterday) { this.dateyesterday = dateyesterday; }

    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) {  this.pageNumber = pageNumber; }
    public int getPageSize() {      return pageSize;    }
    public void setPageSize(int pageSize) {        this.pageSize = pageSize;    }
    public long getCountRecords() {        return countRecords;    }
    public void setCountRecords(long countRecords) {        this.countRecords = countRecords;    }
    public int getMaxPageNumber() {        return maxPageNumber;    }
    public void setMaxPageNumber(int maxPageNumber) {        this.maxPageNumber = maxPageNumber;    }

    //установка цвета строки таблицы в зависимости от значения transferstatus
    public String getRowClass() {

        StringBuilder rowClasses = new StringBuilder();
        for (MbankingSms transfer : mbankingSmsList) {
            if (rowClasses.length() > 0) rowClasses.append(",");

            if(transfer.getState().intValue() == 0) {
                rowClasses.append("newSmsState");
            }else if(transfer.getState().intValue()== 1){
                rowClasses.append("workerDoneState");
            }else if(transfer.getState().intValue()== 2){
                rowClasses.append("receiverDoneState");
            }else if(transfer.getState().intValue()== 3){
                rowClasses.append("inWorkingState");
            }else if(transfer.getState().intValue()== -1){
                rowClasses.append("notWorkedState");
            }else if(transfer.getState().intValue()== -2){
                rowClasses.append("notParsedWrongCommand");
            }else if(transfer.getState().intValue()== -3){
                rowClasses.append("notWorkedNoDataState");
            }else if(transfer.getState().intValue()== -4){
                rowClasses.append("errorResponseRegState");
            }
        }
        return rowClasses.toString();
    }

    public void getBetweenDatesList(){
        countRecords = mbankingSmsServiceImpl.getCountCriteria(dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
        maxPageNumber = (int) ((countRecords / pageSize));
        logger.info(">>>> maxPageNumber = {}", maxPageNumber);
        logger.info(">>>> countRecords = {}", countRecords);
        mbankingSmsList = null;
        mbankingSmsList = mbankingSmsServiceImpl.getBetweenDatesList((pageNumber-1)*pageSize, pageSize, dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
    }

    public void reloadData(){
        setDefault();
        countRecords = mbankingSmsServiceImpl.getCountCriteria(dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
        maxPageNumber = (int) ((countRecords / pageSize));
        mbankingSmsList = mbankingSmsServiceImpl.getBetweenDatesList((pageNumber-1)*pageSize, pageSize, dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
    }


    public void getNextPage(){
        countRecords = mbankingSmsServiceImpl.getCountCriteria(dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
        maxPageNumber = (int) ((countRecords / pageSize));
        logger.info(">>>> maxPageNumber = {}", maxPageNumber);
        logger.info(">>>> countRecords = {}", countRecords);
        if(pageNumber < maxPageNumber) {
            ++pageNumber;
            mbankingSmsList = mbankingSmsServiceImpl.getBetweenDatesList((pageNumber-1)*pageSize, pageSize, dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
        }
    }

    public void getPreviousPage(){
        if(pageNumber > 1) {
            --pageNumber;
            countRecords = mbankingSmsServiceImpl.getCountCriteria(dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
            mbankingSmsList = mbankingSmsServiceImpl.getBetweenDatesList((pageNumber-1)*pageSize, pageSize, dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
        }
        else{
            countRecords = mbankingSmsServiceImpl.getCountCriteria(dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
            mbankingSmsList = mbankingSmsServiceImpl.getBetweenDatesList((pageNumber-1)*pageSize, pageSize, dateyesterday, datenow, newSmsState, workerDoneState, receiverDoneState, inWorkingState, notWorkedState, notParsedWrongCommand, notWorkedNoDataState, errorResponseRegState, selectOrder );
        }
    }

    public void testButtonInTable(){

    }


    public void onDialogClose(SelectEvent event){
        FacesMessage message=(FacesMessage)event.getObject();
        if(message!=null)
            FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void openLinkedRecords(){
        if(concreeteMbankingSms.getOriginalIdSms() == null) {
            linkedmbankingSmsList = mbankingSmsServiceImpl.findLinkedRecords(concreeteMbankingSms.getId_sms());
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 1024);
            options.put("contentHeight", 320);
            options.put("closable", true);
            options.put("resizable", false);
            RequestContext.getCurrentInstance().openDialog("mbankingsmsDialog", options, null);
        }
        else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("There are no records", "This is not primary record."));
        }
    }

    public void close() {
        RequestContext.getCurrentInstance().closeDialog(new FacesMessage("Действие отменено"));
    }



}
