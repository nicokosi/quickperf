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

package org.quickperf.sql.sending;

import org.quickperf.issue.PerfIssue;
import org.quickperf.issue.VerifiablePerformanceIssue;
import org.quickperf.sql.annotation.ExpectQueriesSending;
import org.quickperf.unit.Count;

public class SqlQueriesSendingVerifier implements VerifiablePerformanceIssue<ExpectQueriesSending, SqlAnalysis> {

    public static final SqlQueriesSendingVerifier INSTANCE = new SqlQueriesSendingVerifier();

    private SqlQueriesSendingVerifier() { }

    @Override
    public PerfIssue verifyPerfIssue(ExpectQueriesSending annotation, SqlAnalysis sqlAnalysis) {

        Count expectedSendingNumber = new Count(annotation.value());

        Count sendingNumber = sqlAnalysis.getQueriesSendingNumber();

        if (!sendingNumber.isEqualTo(expectedSendingNumber)) {
            boolean severalExpectedSendings = expectedSendingNumber.getValue() > 1;
            boolean severalSendings = sendingNumber.getValue() > 1;
            String description =   "You may think that there " + (severalExpectedSendings ? "were" : "was")
                                 + " <" + expectedSendingNumber.getValue() + ">"
                                 + " queries sending" + (severalExpectedSendings ? "s" : "" )
                                 + System.lineSeparator()
                                 + "       " + "But there " + (severalSendings ? "are" : "is") + " <" + sendingNumber.getValue() + ">...";
            return new PerfIssue(description);
        }

        return PerfIssue.NONE;

    }

}