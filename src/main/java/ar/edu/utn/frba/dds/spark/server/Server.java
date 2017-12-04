package ar.edu.utn.frba.dds.spark.server;

import ar.edu.utn.frba.dds.util.BatchCuentas;
import ar.edu.utn.frba.dds.util.ProcesarCuentas;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import spark.Spark;
import spark.debug.DebugScreen;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class Server {
	public static void main(String[] args) {

		//BatchCuentas.verificarCuentas();
        crearJob();
		Spark.port(getHerokuAssignedPort());
        DebugScreen.enableDebugScreen();
		Router.configure();
	}

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    private static void crearJob() {
        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(60)
                        .repeatForever()) //CronScheduleBuilder.cronSchedule("0 * * * * ?")
                .build();

        // define the job and tie it to our MyJob class
        JobDetail job = JobBuilder.newJob(ProcesarCuentas.class)
                .withIdentity("job1", "group1")
                .build();

        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            // and start it off
            scheduler.start();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            System.out.println("Unable to start scheduler service");
        }

    }

}
