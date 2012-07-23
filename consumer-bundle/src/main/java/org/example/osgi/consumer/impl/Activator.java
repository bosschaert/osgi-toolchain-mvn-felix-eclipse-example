package org.example.osgi.consumer.impl;

import org.example.osgi.api.MyService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {
    private ServiceTracker st;

    @Override
    public void start(BundleContext context) throws Exception {
        st = new ServiceTracker(context, MyService.class.getName(), null) {
            @Override
            public Object addingService(ServiceReference reference) {
                Object svc = super.addingService(reference);
                if (svc instanceof MyService) {
                    invokeService((MyService) svc);
                }
                return svc;
            }
        };
        st.open();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        st.close();
    }

    void invokeService(MyService svc) {
        String input = "Testing123";
        System.out.println("Invoking Service with input: " + input);
        System.out.println("  Result: " + svc.doSomething(input));
    }
}
