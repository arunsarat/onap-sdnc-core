/*-
 * ============LICENSE_START=======================================================
 * openECOMP : SDN-C
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights
 * 						reserved.
 * ================================================================================
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
 * ============LICENSE_END=========================================================
 */

package org.openecomp.sdnc.sli.provider;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.openecomp.sdnc.sli.SvcLogicContext;
import org.openecomp.sdnc.sli.SvcLogicException;
import org.openecomp.sdnc.sli.SvcLogicNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwitchNodeExecutor extends SvcLogicNodeExecutor {

	private static final Logger LOG = LoggerFactory
			.getLogger(SwitchNodeExecutor.class);
	
	@Override

	public SvcLogicNode execute(SvcLogicServiceImpl svc, SvcLogicNode node,
			SvcLogicContext ctx) throws SvcLogicException {


		String testResult = evaluateNodeTest(node, ctx);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Executing switch node");
			

			LOG.debug("test expression (" + node.getAttribute("test")
					+ ") evaluates to " + testResult);
		}

		SvcLogicNode nextNode = node.getOutcomeValue(testResult);

		if (LOG.isDebugEnabled()) {
			if (nextNode != null) {
                LOG.debug("Next node to execute is node " + nextNode.getNodeId());
			} else {
				LOG.debug("No next node found");
			}
		}
		return (nextNode);

	}
}