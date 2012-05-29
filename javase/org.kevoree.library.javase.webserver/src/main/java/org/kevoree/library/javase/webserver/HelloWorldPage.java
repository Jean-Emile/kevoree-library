package org.kevoree.library.javase.webserver;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Port;
import org.kevoree.framework.MessagePort;

@ComponentType
public class HelloWorldPage extends AbstractPage {

    @Port(name = "request")
    public void requestHandler(Object param) {
        KevoreeHttpRequest request = resolveRequest(param);
        if (request != null) {
            KevoreeHttpResponse response = buildResponse(request);
            StringBuilder builder = new StringBuilder();
            builder.append("<html><body>");
            builder.append("Hello from Kevoree from url " + request.getUrl() + " <br />");
            for (String key : request.getResolvedParams().keySet()) {
                builder.append(key + "->" + request.getResolvedParams().get(key) + "<br>");
            }
            builder.append("Served by node "+this.getNodeName()+"<br />");
            builder.append("</body></html>");
            response.setContent(builder.toString());
            this.getPortByName("content", MessagePort.class).process(response);//SEND MESSAGE
        }
    }

}
