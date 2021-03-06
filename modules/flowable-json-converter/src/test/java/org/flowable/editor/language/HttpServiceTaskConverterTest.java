/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.flowable.editor.language;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FieldExtension;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.HttpServiceTask;
import org.flowable.bpmn.model.ServiceTask;
import org.junit.jupiter.api.Test;

public class HttpServiceTaskConverterTest extends AbstractConverterTest {

    @Test
    public void convertJsonToModel() throws Exception {
        BpmnModel bpmnModel = readJsonFile();
        validateModel(bpmnModel);
    }

    @Test
    public void doubleConversionValidation() throws Exception {
        BpmnModel bpmnModel = readJsonFile();
        bpmnModel = convertToJsonAndBack(bpmnModel);
        validateModel(bpmnModel);
    }

    @Override
    protected String getResource() {
        return "test.httpservicetaskmodel.json";
    }

    private void validateModel(BpmnModel model) {
        FlowElement flowElement = model.getMainProcess().getFlowElement("servicetask", true);
        assertThat(flowElement).isNotNull();
        assertThat(flowElement).isInstanceOf(HttpServiceTask.class);
        assertThat(flowElement.getId()).isEqualTo("servicetask");
        ServiceTask serviceTask = (ServiceTask) flowElement;
        assertThat(serviceTask.getId()).isEqualTo("servicetask");
        assertThat(serviceTask.getName()).isEqualTo("REST");

        List<FieldExtension> fields = serviceTask.getFieldExtensions();
        assertThat(fields).hasSize(2);
    }
}
