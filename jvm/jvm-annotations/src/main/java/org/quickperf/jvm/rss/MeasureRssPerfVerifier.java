/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 * Copyright 2019-2020 the original author or authors.
 */

package org.quickperf.jvm.rss;

import org.quickperf.issue.PerfIssue;
import org.quickperf.issue.VerifiablePerformanceIssue;
import org.quickperf.jvm.annotations.MeasureRSS;

public class MeasureRssPerfVerifier implements VerifiablePerformanceIssue<MeasureRSS, ProcessStatus> {
    public static final VerifiablePerformanceIssue INSTANCE = new MeasureRssPerfVerifier();

    private MeasureRssPerfVerifier(){
    }

    @Override
    public PerfIssue verifyPerfIssue(MeasureRSS annotation, ProcessStatus measure) {
        System.out.println("[QUICK PERF] Measured RSS " + measure.getRssInKb() + " kb for process " + measure.getPid());
        return PerfIssue.NONE;
    }
}
