package org.osate.analysis.flows;

import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.EndToEndFlowInstance;
import org.osate.aadl2.instance.FlowElementInstance;
import org.osate.aadl2.instance.FlowSpecificationInstance;
import org.osate.analysis.flows.model.LatencyContributor;

public class FlowLatencyLogic {
	public static LatencyContributor mapFlowElementInstance(final EndToEndFlowInstance etef,
			final FlowElementInstance flowElementInstance) {

//		OsateDebug.osateDebug("FlowLatencyUtil", "fei = " + flowElementInstance);
//		OsateDebug.osateDebug("FlowLatencyUtil", "fei= name" + flowElementInstance.getName());

		if (flowElementInstance instanceof FlowSpecificationInstance) {

			return FlowLatencyLogicComponent.mapComponentInstance(etef, flowElementInstance);
		}

		if (flowElementInstance instanceof ConnectionInstance) {
			return FlowLatencyLogicConnection.mapConnectionInstance(etef, flowElementInstance);
		}

		return null;
	}

}
