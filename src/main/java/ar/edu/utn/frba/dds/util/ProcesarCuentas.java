package ar.edu.utn.frba.dds.util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Julian Latasa on 4/12/2017.
 */
public class ProcesarCuentas implements  org.quartz.Job {

    public ProcesarCuentas() {

    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Cargar una cuenta");
        BatchCuentas.verificarCuentas();
    }
}
