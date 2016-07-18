package com.newrelic.airline.reservations;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Scheduled job that runs at 12:05 AM and deletes reservations whose return dates are in the past.  It also deletes the old capacity records (i.e. seatmap)
 * 
 * @author dhilpipre
 *
 */
public class DatabasePurge implements Job {
	
	private static final Logger LOG = Logger.getLogger(DatabasePurge.class);
	
	public static boolean initialize() {
		boolean successful = true;
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			JobDetail job = JobBuilder.newJob(DatabasePurge.class).withIdentity("Database Purge","Purge").build();
			
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("DatabasePurgeTrigger","Purge").startNow().withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 5)).build();
			
			sched.scheduleJob(job, trigger);
			Date runTime = trigger.getNextFireTime();
			LOG.info(job.getKey()+" will run at: " + runTime);
			
			sched.start();
		} catch (SchedulerException e) {
			LOG.error("Error scheduling purge task", e);
			successful = false;
		}
		
		return successful;
	}

	@Override
	public void execute(JobExecutionContext jobExeCtx) throws JobExecutionException {

		Date runtime = jobExeCtx.getFireTime();
		Date next = jobExeCtx.getNextFireTime();
		LOG.info("Runtime of purge is "+runtime);
		LOG.info("Next runtime is " + next);
		ReservationController controller = ReservationControllerFactory.getReservationController();
		if(ReservationControllerImpl.class.isInstance(controller)) {
			((ReservationControllerImpl)controller).purgeReservations();
			((ReservationControllerImpl)controller).purgeCapacity();
			((ReservationControllerImpl)controller).addNewCapacity();
		}
	}
	
 }
