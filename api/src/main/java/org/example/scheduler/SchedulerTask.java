package org.example.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.RequestTimer;
import org.example.postgres.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@Slf4j
public class SchedulerTask {

    @Autowired
    private SenderEmailService senderEmailService;

    @Autowired
    private BorrowBookService borrowBookService;

    @Scheduled(cron = "30 * * * * *")
    public void doit() {
        Date yesterday = getYesterday();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strYesterday = dateFormat.format(yesterday);
        long count = borrowBookService.countByTimer(new RequestTimer(yesterday, yesterday));
        String body = "Số lượng sách mượn ngày " + strYesterday + "là: " + count;
        String subject = "Số lượng sách mượn ngày " + strYesterday;
        senderEmailService.sendSimpleEmail(body, subject);
        log.info("Số lượng sách được gửi là thống kê của ngày " + strYesterday);
        log.info("Đã gửi email thành công vào lúc " + new Date());
    }

    public Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }
}
