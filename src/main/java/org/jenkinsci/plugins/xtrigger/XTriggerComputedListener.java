package org.jenkinsci.plugins.xtrigger;

import hudson.DescriptorExtensionList;
import hudson.Extension;
import hudson.lifecycle.Lifecycle;
import hudson.model.Computer;
import hudson.model.TaskListener;
import hudson.slaves.ComputerListener;
import hudson.triggers.Trigger;
import hudson.triggers.TriggerDescriptor;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Gregory Boissinot
 */
@Extension
public class XTriggerComputedListener extends ComputerListener {

    @Override
    public void onOnline(Computer c, TaskListener listener) throws IOException, InterruptedException {
        listener.getLogger().println("The following triggers are available for your jobs");
        DescriptorExtensionList<Trigger<?>,TriggerDescriptor> descriptors = Trigger.all();
        Iterator<TriggerDescriptor> triggerIterator = descriptors.iterator();
        while (triggerIterator.hasNext()){
            listener.getLogger().println(String.format("[Trigger] - %s", triggerIterator.next().getDisplayName()));
        }
    }
}
