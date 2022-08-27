package io.asyph.problemcontestservice.jobs;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import io.asyph.problemcontestservice.models.Contest;
import io.asyph.problemcontestservice.models.ContestStatus;
import io.asyph.problemcontestservice.repository.ContestRepository;

public class ContestStatusJob implements Job {
    final Logger logger = LogManager.getLogger(ContestStatusJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        Contest contest = (Contest)context.getMergedJobDataMap().get("contestKey");
        ContestRepository repository = (ContestRepository)context.getMergedJobDataMap().get("repositoryKey");
        SchedulerFactory factory = (SchedulerFactory) context.getMergedJobDataMap().get("factoryKey");
        if(contest.getStatus().equals(ContestStatus.NOT_STARTED)) {
            Trigger oldTrigger = context.getTrigger();
            LocalDateTime time = contest.getEndTime();
            String cronExpression =
                time.getSecond() + " " +
                time.getMinute() + " " +
                time.getHour() + " " +
                time.getDayOfMonth() + " " +
                time.getMonthValue() + " " +
                "?" + " " +
                time.getYear();
            contest.setStatus("RUNNING");
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                                      .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
            try {
                factory.getScheduler().rescheduleJob(oldTrigger.getKey(), cronTrigger);
                contest.setSchedulerId(cronTrigger.getKey().toString());
            }
            catch(Exception e) {
                logger.error("Exception occured while rescheduling" + e.getStackTrace());
            }
        }
        else if(contest.getStatus().equals(ContestStatus.RUNNING)) {
            contest.setStatus("FINISHED");
        }
        repository.save(contest);
    }
}
