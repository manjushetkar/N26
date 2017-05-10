package com.ms.n26.com.ms.n26.controller;

import com.ms.n26.com.ms.n26.domain.Statistics;
import com.ms.n26.com.ms.n26.domain.TransactionItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by mshetkar on 5/9/2017.
 */
@RestController
public class TranscationsController {

    public static final int ONE_MINUTE = 60000;
    Queue<TransactionItem> transactionItemQueue = new LinkedList<TransactionItem>();

    @RequestMapping(path = "/transactions", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity saveTransaction(@RequestBody TransactionItem item) {

        /*
            Maintain queue for transactions in last 60 seconds,
             if 60 seconds is passed then clear the queue
        */
        if(transactionItemQueue.peek() != null && (item.getTimestamp() - transactionItemQueue.peek().getTimestamp() >= ONE_MINUTE)) {
            transactionItemQueue.clear();
        }

        /* return BAD_REQUEST if either timestamp or amount is missing */
        if(item.getTimestamp() != null && item.getAmount() != null) {
            transactionItemQueue.add(item);
        } else { /* throw an exception if timestamp or amount is missing */
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/statistics", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistics> getTransaction() {
        long timeNow = new Date().getTime();
        Statistics statistics = new Statistics();

        if(transactionItemQueue.peek() != null &&
                (timeNow -  transactionItemQueue.peek().getTimestamp() > 0 &&
                        timeNow -  transactionItemQueue.peek().getTimestamp() <= ONE_MINUTE)) {
            for (TransactionItem item : transactionItemQueue) {
                statistics.setSum(statistics.getSum() + item.getAmount());
                statistics.setCount(transactionItemQueue.size());
                if (statistics.getMax() < item.getAmount()) {
                    statistics.setMax(item.getAmount());
                }
                if (statistics.getMin() > item.getAmount()) {
                    statistics.setMin(item.getAmount());
                }
            }
        }
        if(statistics.getCount() > 0) {
            statistics.setAvg(statistics.getSum() / statistics.getCount());
        }
        return  ResponseEntity.ok().body(statistics);
    }
}
