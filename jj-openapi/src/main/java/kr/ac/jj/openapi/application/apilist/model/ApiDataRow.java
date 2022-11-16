package kr.ac.jj.openapi.application.apilist.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import kr.ac.jj.shared.infrastructure.framework.core.support.collection.CamelKeyMap;

@JacksonXmlRootElement(localName = "row")
public class ApiDataRow extends CamelKeyMap {

    private static final long serialVersionUID = 302843306200329258L;

}
