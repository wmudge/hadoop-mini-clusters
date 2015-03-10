/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.sakserv.minicluster;

import com.github.sakserv.minicluster.config.ConfigVars;
import com.github.sakserv.minicluster.config.PropertyParser;
import com.github.sakserv.minicluster.impl.MRLocalCluster;
import com.github.sakserv.minicluster.impl.YarnLocalCluster;
import com.github.sakserv.simpleyarnapp.Client;
import org.apache.hadoop.conf.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MRLocalClusterIntegrationTest {
    
    // Logger
    private static final Logger LOG = LoggerFactory.getLogger(MRLocalClusterIntegrationTest.class);

    // Setup the property parser
    private static PropertyParser propertyParser;
    static {
        try {
            propertyParser = new PropertyParser(ConfigVars.DEFAULT_PROPS_FILE);
        } catch(IOException e) {
            LOG.error("Unable to load property file: " + propertyParser.getProperty(ConfigVars.DEFAULT_PROPS_FILE));
        }
    }
    
    private static MRLocalCluster mrLocalCluster;
    
    @BeforeClass
    public static void setUp() throws IOException {
        mrLocalCluster = new MRLocalCluster.Builder()
            .setNumNodeManagers(Integer.parseInt(propertyParser.getProperty(ConfigVars.MR_NUM_NODE_MANAGERS_KEY)))
            .setYarnConfig(new Configuration())
            .build();

        mrLocalCluster.start();
    }

    @AfterClass
    public static void tearDown() {
        mrLocalCluster.stop();
    }

    @Test
    public void testYarnLocalCluster() {
        LOG.info("TESTING");
        
/*        String[] args = new String[7];
        args[0] = "uptime";
        args[1] = "2";
        args[2] = getClass().getClassLoader().getResource("simple-yarn-app-1.1.0.jar").toString();
        args[3] = yarnLocalCluster.getResourceManagerAddress();
        args[4] = yarnLocalCluster.getResourceManagerHostname();
        args[5] = yarnLocalCluster.getResourceManagerSchedulerAddress();
        args[6] = yarnLocalCluster.getResourceManagerResourceTrackerAddress();
        
        try {
            Client.main(args);
        } catch(Exception e) {
            e.printStackTrace();
        }*/
    }
}