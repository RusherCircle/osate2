/*
* generated by Xtext
*/
package org.osate.xtext.aadl2.errormodel.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class ErrorModelAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/osate/xtext/aadl2/errormodel/parser/antlr/internal/InternalErrorModelParser.tokens");
	}
}