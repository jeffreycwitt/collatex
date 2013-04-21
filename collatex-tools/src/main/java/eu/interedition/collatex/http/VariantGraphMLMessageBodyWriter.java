/*
 * Copyright (c) 2013 The Interedition Development Group.
 *
 * This file is part of CollateX.
 *
 * CollateX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CollateX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CollateX.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.interedition.collatex.http;

import com.google.common.io.Closeables;
import eu.interedition.collatex.VariantGraph;
import eu.interedition.collatex.simple.SimpleVariantGraphSerializer;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author <a href="http://gregor.middell.net/" title="Homepage">Gregor Middell</a>
 */
@Provider
@Produces("application/graphml+xml")
public class VariantGraphMLMessageBodyWriter implements MessageBodyWriter<VariantGraph> {

  private static final XMLOutputFactory XML_OUTPUT_FACTORY = XMLOutputFactory.newInstance();

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return VariantGraph.class.isAssignableFrom(type);
  }

  @Override
  public long getSize(VariantGraph variantGraph, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return -1;
  }

  @Override
  public void writeTo(VariantGraph variantGraph, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
    XMLStreamWriter xml = null;
    try {
      xml = XML_OUTPUT_FACTORY.createXMLStreamWriter(entityStream);
      xml.writeStartDocument();
      new SimpleVariantGraphSerializer(variantGraph).toGraphML(xml);
      xml.writeEndDocument();
    } catch (XMLStreamException e) {
      throw new IOException(e);
    } finally {
      try {
        xml.close();
      } catch (XMLStreamException e) {
      }
      Closeables.close(entityStream, false);
    }
  }
}
