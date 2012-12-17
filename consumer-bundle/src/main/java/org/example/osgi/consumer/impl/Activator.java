/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
