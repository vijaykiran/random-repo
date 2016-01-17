package org.lunatech.airports.console.step;

/**
 * Created by Anastasia on 19.09.2015.
 */
public class EntryStep extends AbstractStep {
    private StepQuery stepQuery = new StepQuery();
    private StepReport stepReport = new StepReport();

    @Override
    protected void doExecute() {
        System.out.println("Select an option: 1 - Query, 2 - Reports, 0 - exit.");
        int command = getCommand();

        switch (command){
            case 1:
                stepQuery.doExecute();
                break;
            case 2:
                stepReport.doExecute();
                break;
            case 0:
                break;
            default:
                unknownCommand();
                break;
        }
    }
}
