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
package org.example.osgi.svc;

import javax.inject.Inject;
import org.example.osgi.api.MyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
 
@RunWith(JUnit4TestRunner.class)
public class SampleTest {
 
	@Inject
	private BundleContext ctx;
 
    @Configuration
    public Option[] config() {
 
        return CoreOptions.options(
    		CoreOptions.mavenBundle("org.example.osgi.mvn-felix-eclipse", "api-bundle"),
    		CoreOptions.mavenBundle("org.example.osgi.mvn-felix-eclipse", "service-bundle"),
            CoreOptions.junitBundles()
            );
    }
 
    @Test
    public void getHelloService() {
    	ServiceReference ref = ctx.getServiceReference(MyService.class.getName());
		MyService svc = (MyService) ctx.getService(ref);
		
		Assert.assertEquals("This service implementation should reverse the input",
				"4321", svc.doSomething("1234"));
    }
}