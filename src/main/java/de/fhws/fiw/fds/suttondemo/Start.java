/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.suttondemo;

import de.fhws.fiw.fds.sutton.server.database.hibernate.DatabaseInstaller;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "de.fhws.fiw.fds")
public class Start extends SpringBootServletInitializer {

    public static final String CONTEXT_PATH = "sd";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SuttonDemoJerseyApplication.class);
    }

    public static void main(String[] args) {
        new Start().configure(
                new SpringApplicationBuilder(Start.class)).run(args);

        new DatabaseInstaller().install();
    }
}
